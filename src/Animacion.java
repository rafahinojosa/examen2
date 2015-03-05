
import java.awt.Image;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
     
        public class Animacion{
	
	// Arreglo de objetos que guardará los cuadros de la animación
         private ArrayList cuadros;
         //Índice del cuadro en el que actualmente se encuentra la animación
         private int indiceCuadroActual;
         //Tiempo que la animación lleva corriendo.
         private long tiempoDeAnimacion;
         //Duración total de la animación
         private long duracionTotal;
	
	/**
		Crea una nueva Animacion vacía
	*/
	public Animacion(){
		cuadros = new ArrayList();
		duracionTotal = 0;
		iniciar();
	}
	
	/**
		Añade una cuadro a la animación con la duración
		indicada (tiempo que se muestra la imagen).
	*/	
	public synchronized void sumaCuadro(Image imagen, long duracion){
		duracionTotal += duracion;
		cuadros.add(new cuadroDeAnimacion(imagen, duracionTotal));
	}
	
	// Inicializa la animación desde el principio. 
	public synchronized void iniciar(){
		tiempoDeAnimacion = 0;
		indiceCuadroActual = 0;
	}
	
	/**
		Actualiza la imagen (cuadro) actual de la animación,
		si es necesario.
	*/
	public synchronized void actualiza(long tiempoTranscurrido){
		if (cuadros.size() > 1){
			tiempoDeAnimacion += tiempoTranscurrido;
			
			if (tiempoDeAnimacion >= duracionTotal){
				tiempoDeAnimacion = tiempoDeAnimacion % duracionTotal;
				indiceCuadroActual = 0; 
			}
			
			while (tiempoDeAnimacion > getCuadro(indiceCuadroActual).tiempoFinal){
				indiceCuadroActual++;
			}
		}
	}
	
	/**
		Captura la imagen actual de la animación. Regeresa null
		si la animación no tiene imágenes.
	*/
	public synchronized Image getImagen(){
		if (cuadros.size() == 0){
			return null;
		}
		else {
			return getCuadro(indiceCuadroActual).imagen;
		}
	}
	
	private cuadroDeAnimacion getCuadro(int i){
		return (cuadroDeAnimacion)cuadros.get(i);
	}
        
        public class cuadroDeAnimacion {

    Image imagen;
    long tiempoFinal;

    public cuadroDeAnimacion(){
            this.imagen = null;
            this.tiempoFinal = 0;
    }

    public cuadroDeAnimacion(Image imagen, long tiempoFinal){
            this.imagen = imagen;
            this.tiempoFinal = tiempoFinal;
    }

    public Image getImagen(){
            return imagen;
    }

    public long getTiempoFinal(){
            return tiempoFinal;
    }

    public void setImagen (Image imagen){
            this.imagen = imagen;
    }

    public void setTiempoFinal(long tiempoFinal){
            this.tiempoFinal = tiempoFinal;
    }
}
    }


