public class Cao {
    /*
     * Se um um atributo nao for estatico(static) nao pode ser acessada em um metodo estatico(static)
     * declarando esta variavel como static ela torna-se como uma variavel global que so e criada uma unica vez e ela 
     * pertence a classe cao e nao ao objecto cao.
     * Nao sera possivel acessar a variavel ossosComidos com a palavra chave this.
     * Porque essa variavel nao pertence a um objecto mas a classe Cao.
     * Para acessar o atributo e feito da seguinte forma: Cao.ossosComidos;
    */ 
    public static int ossosComidos;// variavel global
    public int ossos;//total de ossos  

    /*
     * se numa classe possuem metodos estaticos nao sera necessario instanciar essa classe para acessar os seus metodos
    */
    public Cao comerOssos() {// esse metodo somente faz a contagem de ossos comidos pelo cao
        this.ossos++;
        Cao.ossosComidos++;
        return this;
    }

    /*
     * metodos estaticos nao podem acessar variaveis e metodos locais, nao e permitido usar a palavra-chave this num
     * metodo estatico porque o this representa um instancia de objecto
     * pode acessar directamente as variaveis estaticas sem precisar chamar o nome da classe
    */
    public static double mediaOssosComidos(int ossos){
        return ossosComidos / ossos;
    }
}