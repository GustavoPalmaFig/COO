import java.io.IOException;

//exemplo de relatório
public class Relatorio {
    public static void main(String[] args) throws IOException {
        Produto [] produtos = GeradorDeRelatoriosV2.carregaProdutos();
       
        Ordenacao ordenacao = new Ordenacao();
        
        Filtro filtro = new FiltraProdutos(new Preco(30.0,300.0));

        String ordem = "crescente";

        ordenacao.setAlgoritmoOrdenacao(new ALG_QUICKSORT(new CRIT_PRECO_CRESC(), ordem));


        GeradorDeRelatoriosV2 relatorio = new GeradorDeRelatoriosV2(produtos, ordenacao, filtro);

        try{
			relatorio.geraRelatorio("saida.html");
		}
		catch(IOException e){
			
			e.printStackTrace();
		}
    }
}