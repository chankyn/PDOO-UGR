# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module ModeloQytetetRuby
  class Especulador < Jugador
    #@@factor_especulador
    
    def initialize(jugador,fianza)
      super(jugador.nombre,jugador.casilla_actual,jugador.encarcelado,jugador.saldo,jugador.carta_libertad,jugador.propiedades,2)
      @fianza = fianza
    end
    def pagar_impuesto(cantidad)
      impuesto = cantidad/2
      modificar_saldo(-impuesto)
    end
    def ir_a_carcel(casilla)
      if (!pagar_fianza(fianza))
            super.ir_a_carcel(casilla);
      end
    end
    def convertirme(fianza)
      @fianza = fianza
      return self
    end
    def pagar_fianza(cantidad)
      resultado = false
      if (tengo_saldo(cantidad))
        modificar_saldo(-cantidad)
        resultado = true
      end
      return resultado
    end
    protected :pagar_impuesto
    private :pagar_fianza
  end
end
