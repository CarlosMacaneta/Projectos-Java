public class TesteCao {

    public static void main(String[] args) {

        Cao rafeiro = new Cao(); //instanciando objecto cao 
        Cao pastor = new Cao();

        //alimentando os caes
        rafeiro.comerOssos().comerOssos(); //alimentado o cao 2 vezes
        pastor.comerOssos().comerOssos().comerOssos().comerOssos();//alimentando o cao 4 vezes

        System.out.println("Rafeiro comeu: "+rafeiro.ossos);//total de ossos comidos
        System.out.println("Pastor comeu: "+pastor.ossos);//total de ossoa comidos
        /*
          * Cao.ossosComidos e a forma de acessar a variavel que pertence a classe cao e nao e preciso instanciar para usar a var
          * o atributo osso nao sera possivel acessar porque nao pertence a classe cao mas sim ao objecto cao.
        */
        System.out.println("Total de ossos comido pelos 2: "+Cao.ossosComidos);//total de ossos comidos pelos caes
        /**
         * nao e necessario instanciar a classe cao para acessar o metodo mediaOssosComidos porque ela e estatica
         */
        System.out.println("Media de ossos comidos: "+Cao.mediaOssosComidos(2));//media dos ossos comidos
    }
    
}