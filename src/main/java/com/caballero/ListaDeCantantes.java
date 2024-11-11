package com.caballero;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListaDeCantantes {

        @JsonProperty("cantantes")
        private List<Cantante> cantantes;

        public ListaDeCantantes() {
        }

        public ListaDeCantantes(List<Cantante> cantantes) {
            this.cantantes = cantantes;
        }

        public List<Cantante> getCantantes() {
            return cantantes;
        }

        public void setCantantes(List<Cantante> cantantes) {
            this.cantantes = cantantes;
        }

        @Override
        public String toString() {
            return "ListaDeCantantes{" +
                    "cantantes=" + cantantes +
                    '}';
        }
}
