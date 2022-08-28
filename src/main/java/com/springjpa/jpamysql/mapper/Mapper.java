package com.springjpa.jpamysql.mapper;

import com.springjpa.jpamysql.entities.Lenguajes;
import com.springjpa.jpamysql.entities.Profesor;
import com.springjpa.jpamysql.model.MProfessor;
import com.springjpa.jpamysql.model.MProfesorLenguaje;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("mapper")
public class Mapper {
    public static List<MProfessor> convertirLista(List<Profesor> profesores){
        List<MProfessor> mProfessors = new ArrayList<>();
        for (Profesor profesor: profesores){
            mProfessors.add(new MProfessor(profesor));
        }
        return mProfessors;
    }

   /* public static List<MProfesorLenguaje> convertirListaLenguajeProfesor(Collection<Lenguajes> lengua){
        List<MProfesorLenguaje> mLenguajeProfessors = new ArrayList<>();
        for (Lenguajes lenguaje: lengua){
            for (Profesor profe: lenguaje.getProfesores()){
                mLenguajeProfessors.add(new MProfesorLenguaje(profe,lenguaje));
            }
        }
        return mLenguajeProfessors;
    }*/
   //*******Mapea los lenguajes que sabe un profesor
   //para mapear los datos de lenguajes q sabe un profesor
   //usado en el endpoint "getlenguajes_profesor"
   //(dado el id del profesor obtengo los lenguajes)
    public static List<MProfesorLenguaje> convertirListaLenguajeProfesor(Profesor profesor){
       List<MProfesorLenguaje> mLenguajeProfessors = new ArrayList<>();
       for (Lenguajes lenguaje: profesor.getLenguajes()){
           //for (Profesor profe: lenguaje.getProfesores()){
               mLenguajeProfessors.add(new MProfesorLenguaje(profesor,lenguaje));
           //}
       }
       return mLenguajeProfessors;
   }

    public static List<MProfesorLenguaje> convertirListaProfesorLenguaje(Lenguajes lenguajes){
        List<MProfesorLenguaje> mLenguajeProfessors = new ArrayList<>();
        for (Profesor profesor: lenguajes.getProfesores()){
            //for (Profesor profe: lenguaje.getProfesores()){
            mLenguajeProfessors.add(new MProfesorLenguaje(profesor,lenguajes));
            //}
        }
        return mLenguajeProfessors;
    }

}
