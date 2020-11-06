import java.util.LinkedList;

public interface Filtro {
    
    public Produto[] filtra(Produto [] produtos);
}

interface CriterioFiltro {

    public boolean compara(Produto produto);
}

class FiltraProdutos implements Filtro {

    private CriterioFiltro criterio;

    public FiltraProdutos(CriterioFiltro criterio){

        this.criterio = criterio;
    }

    public Produto[] filtra(Produto [] produtos) {
        
        LinkedList<Produto> filtro = new LinkedList<>();

        for(Produto produto : produtos){
            if(criterio.compara(produto))
                filtro.add(produto);
        }

        Produto [] array = new Produto[filtro.size()];
        for(int i = 0; i < array.length; i++) array[i] = filtro.get(i);

        return array;
    }
}

class Preco implements CriterioFiltro {

    double min;
    double max;

    public Preco(double min, double max){

        this.min = min;
        this.max = max;
    }

    public boolean compara(Produto produto) {
        
        return produto.getPreco() > min && produto.getPreco() < max;
    }
}

class Descricao implements CriterioFiltro {

    private String substring;

    public Descricao(String substring){

        this.substring = substring;
    }
    
    public boolean compara(Produto produto) {
    
        return produto.getDescricao().contains(substring);
    }
}

class Quantidade implements CriterioFiltro {

    private int n;

    public Quantidade(int n){

        this.n = n;
    }

    public boolean compara(Produto produto) {
    
        return produto.getQtdEstoque() <= n;
    }
}

class Categoria implements CriterioFiltro {

    private String categoria;

    public Categoria(String categoria){

        this.categoria = categoria;
    }

    public boolean compara(Produto produto) {

        return produto.getCategoria().equals(categoria);
    }
}