abstract class Decorator implements Produto {

    Produto produto;
    
    public void setQtdEstoque(int qtdEstoque){
        produto.setQtdEstoque(qtdEstoque);

    } 
	public void setPreco(double preco){
        produto.setPreco(preco);
    }
	
	public int getId(){
        return produto.getId();
    }
	public String getDescricao(){
        return produto.getDescricao();
    }
	public String getCategoria(){
        return produto.getCategoria();
    }
	public int getQtdEstoque(){
        return produto.getQtdEstoque();
    }
	public double getPreco(){
        return produto.getPreco();
    }
    public String formataParaImpressao(){
        return produto.formataParaImpressao();

    }
}

class Negrito extends Decorator {
    public Negrito (Produto produto){
        this.produto = produto;

    }
    public String formataParaImpressao(){
        
    return "<span style=\"font-weight:bold\">"+ produto.formataParaImpressao()+"</span>";
     }
} 
    
class Italico extends Decorator {
    public Italico (Produto produto){
        this.produto = produto;
 
    }
    public String formataParaImpressao(){
         
        return "<span style=\"font-style:italic\">"+ produto.formataParaImpressao()+"</span>";
    }
} 
     
class Cor extends Decorator {
    String insiraCor;
    public Cor (Produto produto, String insiraCor){
        this.produto = produto;
        this.insiraCor = insiraCor;
    }
    public String formataParaImpressao(){
         
        return "<FONT COLOR=\""+insiraCor+"\">"+ produto.formataParaImpressao()+"</FONT>";
    }
} 
