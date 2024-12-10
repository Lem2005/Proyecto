/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.Proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Proyecto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
