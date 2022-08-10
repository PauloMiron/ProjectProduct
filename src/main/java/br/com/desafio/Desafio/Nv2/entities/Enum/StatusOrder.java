package br.com.desafio.Desafio.Nv2.entities.Enum;

public enum StatusOrder {

    Pending(1),
    Finished(2);

    private int cod;

    private StatusOrder(int cod){
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public static StatusOrder toEnum(int cod){
        for (StatusOrder x : StatusOrder.values()){
            if (x.getCod() == cod){
                return x;
            }
        }
        throw new IllegalArgumentException("Código Invalido");
    }
}
