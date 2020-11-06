public class Ordenacao {

    String ordem;

    AlgoritmoOrdenacao algoritmo = null;
    EscolhaCriterio criterio = null;

    public void setAlgoritmoOrdenacao(AlgoritmoOrdenacao algoritmo){

		this.algoritmo = algoritmo;
	}
	
	public void setCriterio(EscolhaCriterio criterio, String ordem){
	
        this.criterio = criterio;
        this.ordem = ordem;
    }
    
    
    public void executarOrdenacao(Produto [] produtos){
        
		if(this.algoritmo != null) this.algoritmo.ordenar(produtos, 0, produtos.length-1);
	}
} 

interface AlgoritmoOrdenacao {

	public void ordenar (Produto [] produtos, int inicio, int fim);
}

interface EscolhaCriterio {

    public boolean criterio (Produto produto1, Produto produto2);
    
}

class ALG_INSERTIONSORT implements AlgoritmoOrdenacao{

    EscolhaCriterio criterio;
    String ordem;

    public ALG_INSERTIONSORT(EscolhaCriterio criterio, String ordem){
        this.criterio = criterio;
        this.ordem = ordem;
    }

    public void ordenar(Produto [] produtos, int inicio, int fim){
        //algoritmo do InsertionSort aqui
        for(int i = inicio; i <= fim; i++){

            Produto x = produtos[i];				
            int j = (i - 1);

            while(j >= inicio){
                if(ordem.equals("crescente")){
                    if(criterio.criterio(x, produtos[j])){
                        produtos[j + 1] = produtos[j];
                        j--;
                    }
                    else break; 
                } else {
                    if(!criterio.criterio(x, produtos[j])){
                        produtos[j + 1] = produtos[j];
                        j--;
                    }
                    else break; 
                }
            }
            produtos[j + 1] = x;
        }
    }
}

class ALG_QUICKSORT implements AlgoritmoOrdenacao{

    EscolhaCriterio criterio;
    String ordem;

    public ALG_QUICKSORT(EscolhaCriterio criterio, String ordem){
        this.criterio = criterio;
        this.ordem = ordem;
    }

    //algoritmo do QuickSort aqui
    private int particiona(Produto [] produtos, int inicio, int fim){

		Produto x = produtos[inicio];
		int i = (inicio - 1);
        int j = (fim + 1);

        while(true){
            if(ordem.equals("crescente")){
               do{

                j--;

               } while(criterio.criterio(x, produtos[j]));
               
               do{

                i++;

               }while(criterio.criterio(produtos[i], x));
            }
            else {
                do{

                    j--;

                } while(criterio.criterio(produtos[j], x));
                   
                do{

                    i++;

                } while(criterio.criterio(x, produtos[i]));
            }

            if(i < j){
				Produto temp = produtos[i];
				produtos[i] = produtos[j]; 				
				produtos[j] = temp;
            }
            
			else return j;
        }

    }

    public void ordenar(Produto [] produtos, int inicio, int fim){
        
        if(inicio < fim) {

            int q = particiona(produtos, inicio, fim);
            
            ordenar(produtos, inicio, q);
            ordenar(produtos, q + 1, fim);
        }
    }
}

class CRIT_DESC_CRESC implements EscolhaCriterio{
    
    public boolean criterio(Produto produto1, Produto produto2){
        //arruma crescentemente a partir da descrição
        if(produto2.getDescricao().compareToIgnoreCase(produto1.getDescricao()) > 0) return true;
        else return false;
    }
}


class CRIT_PRECO_CRESC implements EscolhaCriterio{
    
    public boolean criterio(Produto produto1, Produto produto2){
        //arruma pela ordem crescente do preço
        if(produto2.getPreco() > produto1.getPreco()) return true;
        else return false;
    }
}



class CRIT_ESTOQUE_CRESC implements EscolhaCriterio{
    
    public boolean criterio(Produto produto1, Produto produto2){
        //arruma pela quantidade de estoque
        if(produto2.getQtdEstoque() > produto1.getQtdEstoque()) return true;
        else return false;
    }
}
