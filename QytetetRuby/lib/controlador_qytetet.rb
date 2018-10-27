#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'qytetet.rb'
require_relative 'jugador.rb'
require_relative 'tipo_casilla.rb'
require_relative 'vista_textual_qytetet.rb'
require_relative 'metodo_salir_carcel.rb'
module InterfazTextualQytetet
  class ControladorQytetet
    include ModeloQytetetRuby
    def initialize
      @juego = ModeloQytetetRuby::Qytetet.instance
      @vista = VistaTextualQytetet.new
    end
    def inicializacion_juego
      
      nombres = @vista.obtener_nombre_jugadores
      
      #nombres = Array.new
      #nombres<<"PepitoGrillo";
      #nombres<<"MacarenaLaPortugesa";
      #nombres<<"CamaronDeLaIsla";
      #nombres<<"NapoleonBonaparte";
      
      @juego.inicializar_juego(nombres)
      @jugador = @juego.jugador_actual
      #@vista.mostrar("Jugador Actual: "+@jugador.nombre)
      @casilla = @jugador.casilla_actual
      #@vista.mostrar("Casilla Actual: "+@casilla.numero_casilla.to_s)
      
      @vista.mostrar("#################################")
      @vista.mostrar("#####                        ####")
      @vista.mostrar("#####  Comienzo de Qytetet   ####")
      @vista.mostrar("#####                        ####")
      @vista.mostrar("#################################")
      #@vista.mostrar(@juego.to_s)
    end
    def elegir_propiedad(propiedades) # lista de propiedades a elegir
      @vista.mostrar("\tCasilla\tTitulo");
        
      listaPropiedades= Array.new
      for prop in propiedades  # crea una lista de strings con numeros y nombres de propiedades
        propString= prop.numero_casilla.to_s+' '+prop.titulo.nombre; 
        listaPropiedades<<propString
      end
      seleccion=@vista.menu_elegir_propiedad(listaPropiedades)  # elige de esa lista del menu
      propiedades.at(seleccion)
    end
    def jugador_en_carcel
      libre = false
      if (@vista.menu_salir_carcel==1)
        libre = @juego.intentar_salir_carcel(MetodoSalirCarcel::PAGANDOLIBERTAD)
      else
        libre = @juego.intentar_salir_carcel(MetodoSalirCarcel::TIRANDODADO)
      end
      if(libre)
        @vista.mostrar("El jugador "+@jugador.nombre+" ha conseguido salir de la carcel.")
      else
        @vista.mostrar("El jugador "+@jugador.nombre+" no ha conseguido salir de la carcel. Pasa el turno al siguiente jugador.");
        es_final_juego
      end
      return libre
    end
    def  gestion_inmobiliaria(encarcelado,bancarrota)
      if(!encarcelado && !bancarrota && @jugador.tengo_propiedades)
        opcion = @vista.menu_gestion_inmobiliaria
        while (opcion!=0 && @jugador.tengo_propiedades)
          
          c = elegir_propiedad(@jugador.obtener_propiedades)
          case opcion
          when 1
            if(@juego.edificar_casa(c))
              @vista.mostrar("Se ha edificado una casa en la casilla "+c.numero_casilla.to_s);
              @vista.mostrar("El saldo del jugador "+@jugador.nombre+" desciente a "+@jugador.saldo.to_s);
            else
              @vista.mostrar("No se ha edificado una casa en la casilla "+c.numero_casilla.to_s);
            end
          when 2
            if(@juego.edificar_hotel(c))
              @vista.mostrar("Se ha edificado un hotel en la casilla "+c.numero_casilla.to_s);
              @vista.mostrar("El saldo del jugador "+@jugador.nombre+" desciente a "+@jugador.saldo.to_s);
            else
              @vista.mostrar("No se ha edificado un hotel en la casilla "+c.numero_casilla.to_s);
            end
          when 3
            if(@juego.vender_propiedad(c))
              @vista.mostrar("Se ha vendido la casilla "+c.numero_casilla.to_s);
              @vista.mostrar("El saldo del jugador "+@jugador.nombre+" aumenta a "+@jugador.saldo.to_s);
            else
              @vista.mostrar("No se ha podido vender la casilla "+c.numero_casilla.to_s);
            end
          when 4
            if(@juego.hipotecar_propiedad(c))
              @vista.mostrar("Se ha hipotecado la casilla "+c.numero_casilla.to_s);
              @vista.mostrar("El saldo del jugador "+@jugador.nombre+" aumenta a "+@jugador.saldo.to_s);
            else
              @vista.mostrar("No se ha podido vender la casilla "+c.numero_casilla.to_s);
            end
          when 5
            if(@juego.cancelar_hipoteca(c))
              @vista.mostrar("Se ha cancelado la hipoteca de la casilla "+c.numero_casilla.to_s);
              @vista.mostrar("El saldo del jugador "+@jugador.nombre+" desciende a "+@jugador.saldo.to_s);
            else
              @vista.mostrar("No se ha podido cancelar la hipoteca de la casilla "+c.numero_casilla.to_s);
            end
          end
          if(@jugador.tengo_propiedades)
            opcion = @vista.menu_gestion_inmobiliaria
          end
        end
      end
    end
    def  es_final_juego
      if (@jugador.saldo>0)
        @vista.mostrar("Fin de turno. Pulsa una tecla cuando estes preparado.")
        gets.chomp
        @juego.siguiente_jugador
        @jugador = @juego.jugador_actual
        desarrollo_juego
      else
        @vista.mostrar("El jugador "+@jugador.to_s+" ha caido en bancarrota.");
        rank = @juego.obtener_ranking
        @vista.mostrar("Jugador: \t Saldo: ");
        rank.each{
          |r|
          @vista.mostrar(r.to_s)
        }
      end
    end
    def juego_tipo_sorpresa
      no_tiene_propietario = @juego.aplicar_sorpresa
      if (@jugador.encarcelado)
        @vista.mostrar("El jugador " + @jugador.to_s + " ha sido encarcelado. Pulsa para continuar.")
        gets.chomp
        es_final_juego
      end
      if (@jugador.saldo<=0)
        @vista.mostrar("El jugador " + @jugador.to_s + " ha entrador en bancarrota. Pulsa para continuar.")
        gets.chomp
        es_final_juego
      end
      if(@jugador.casilla_actual != @casilla)
        @vista.mostrar("El jugador " + @jugador.nombre + " ha cambiado de posicion, ahora esta en la casilla " + @jugador.casilla_actual.numero_casilla.to_s)
        @casilla = @jugador.casilla_actual
        if(@casilla.tipo == TipoCasilla::CALLE)
          if(!@casilla.titulo.tengo_propietario)
            if(@vista.elegir_quiero_comprar)
              @juego.comprar_titulo_propiedad
            end
          else
            @vista.mostrar("La casilla " + @casilla.to_s + " tiene propietario, el jugador " + @jugador.nombre + " tiene que pagar "+@casilla.cobrar_alquiler)
          end
        end
      end
      return no_tiene_propietario
    end
    def jugador_libre
      no_tiene_propietario = @juego.jugar
      @casilla = @jugador.casilla_actual
      @vista.mostrar("Pulsa una tecla para avanzar a la casilla: "+@casilla.numero_casilla.to_s)
      gets.chomp
      bancarrota = @jugador.saldo<0
      if(!bancarrota)
        encarcelado = @jugador.encarcelado
        if(!encarcelado)
          if(@casilla.tipo == TipoCasilla::SORPRESA)
            @vista.mostrar("El jugador "+@jugador.to_s+" ha caido en una casilla tipo sorpresa.\n");
            @vista.mostrar("Ha tocado la sopresa: "+@juego.carta_actual.to_s);
            juego_tipo_sorpresa
          elsif(@casilla.tipo == TipoCasilla::CALLE)
            no_tiene_propietario = !@casilla.titulo.tengo_propietario
            if (no_tiene_propietario)
              @vista.mostrar("La calle "<<@casilla.to_s<<" se puede comprar. Elige una opción.")
              if(@vista.elegir_quiero_comprar)
                @juego.comprar_titulo_propiedad
                @vista.mostrar("El jugador "+@jugador.to_s+" ha comprado la casilla "+@casilla.numero_casilla.to_s)
                @vista.mostrar(@jugador.nombre+" se ha quedado con un saldo total de "+@jugador.saldo.to_s)
              else
                @vista.mostrar("El jugador "+@jugador.to_s+" no ha comprado la casilla "+@casilla.numero_casilla.to_s)
              end
            else
              if(!@casilla.esta_hipotecada)
                @vista.mostrar("La casilla "+@casilla.numero_casilla.to_s+" tiene propietario, el jugador "+@jugador.nombre+" tiene que pagar "+@casilla.cobrar_alquiler.to_s)
                @vista.mostrar(@jugador.nombre+" se ha quedado con un saldo total de "+@jugador.saldo.to_s)
              end
            end
          end
        end
          gestion_inmobiliaria(encarcelado,bancarrota)
          es_final_juego
      end
    end
    def desarrollo_juego
      libre = true
      @jugador = @juego.jugador_actual
      @casilla = @jugador.casilla_actual
      
      @vista.mostrar("Turno del jugador: "+@jugador.nombre);
      @vista.mostrar("Esta en la casilla: "+@casilla.numero_casilla.to_s);
      @vista.mostrar("Es una casilla de tipo: "+@casilla.tipo.to_s);
       
      if (@jugador.encarcelado)
        libre = jugador_en_carcel;
      end
      if (libre)
        jugador_libre
      end
    end
    def self.main
      controlador = ControladorQytetet.new
      controlador.inicializacion_juego
      controlador.desarrollo_juego
    end
    
    
  end
  
  ControladorQytetet.main
end
