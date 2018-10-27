# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require "singleton"
module ModeloQytetetRuby
  class Dado
    #Indicamos que es singleton.
    include Singleton
    
    def tirar
      return rand(1..6)
    end
  end
end
