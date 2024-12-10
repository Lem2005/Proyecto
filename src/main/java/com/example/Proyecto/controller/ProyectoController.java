/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Proyecto.controller;

import com.example.Proyecto.dto.Persona;
import com.example.Proyecto.model.Usuario;
import com.example.Proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Proyecto")
public class ProyectoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/numero")
    public List<Integer> obtenerNumeros() {
        Random random = new Random();
        return random.ints(10, 1, 100).boxed().collect(Collectors.toList());
    }

    @PostMapping("/pares")
    public Map<String, List<Integer>> separarParesImpares(@RequestBody List<Integer> numeros) {
        Map<String, List<Integer>> respuesta = new HashMap<>();
        respuesta.put("pares", numeros.stream().filter(n -> n % 2 == 0).collect(Collectors.toList()));
        respuesta.put("impares", numeros.stream().filter(n -> n % 2 != 0).collect(Collectors.toList()));
        return respuesta;
    }

    @PostMapping("/validacorreo")
    public boolean validarCorreo(@RequestBody String correo) {
        return correo.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    @PostMapping("/crearArchivo")
    public String crearArchivo(@RequestBody List<Persona> personas) {
        try (FileWriter writer = new FileWriter("personas.txt")) {
            for (Persona persona : personas) {
                writer.write(persona.getNombre() + "|" +
                        persona.getApellidoPaterno() + "|" +
                        persona.getApellidoMaterno() + "|" +
                        persona.getEdad() + "\n");
            }
            return "Archivo creado exitosamente.";
        } catch (IOException e) {
            return "Error al crear el archivo: " + e.getMessage();
        }
    }

    @PostMapping("/crearUsuario")
    public Long crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario.getId();
    }
}
