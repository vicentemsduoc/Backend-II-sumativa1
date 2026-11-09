package com.petsalud.backendpetsalud.controller;

import com.petsalud.backendpetsalud.dto.UsuarioRegistroDTO;
import com.petsalud.backendpetsalud.dto.UsuarioResponseDTO;
import com.petsalud.backendpetsalud.entity.Rol;
import com.petsalud.backendpetsalud.entity.Usuario;
import com.petsalud.backendpetsalud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody UsuarioRegistroDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDireccion(dto.getDireccion());
        usuario.setRol(Rol.valueOf(dto.getRol().toUpperCase()));

        Usuario usuarioGuardado = usuarioService.registrarUsuario(usuario);

        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(usuarioGuardado.getId());
        response.setNombre(usuarioGuardado.getNombre());
        response.setApellido(usuarioGuardado.getApellido());
        response.setEmail(usuarioGuardado.getEmail());
        response.setTelefono(usuarioGuardado.getTelefono());
        response.setDireccion(usuarioGuardado.getDireccion());
        response.setRol(usuarioGuardado.getRol().name());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioService.listarUsuarios().stream()
                .map(usuario -> {
                    UsuarioResponseDTO dto = new UsuarioResponseDTO();
                    dto.setId(usuario.getId());
                    dto.setNombre(usuario.getNombre());
                    dto.setApellido(usuario.getApellido());
                    dto.setEmail(usuario.getEmail());
                    dto.setTelefono(usuario.getTelefono());
                    dto.setDireccion(usuario.getDireccion());
                    dto.setRol(usuario.getRol().name());
                    return dto;
                })
                .toList();
    }
}
