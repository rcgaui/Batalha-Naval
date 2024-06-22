package Model;

class Jogador { 
	private String nome;
    private int qntSubmarinos;
    private int qntCruzadores;
    private int qntDestroyers;
    private int qntHidroavioes;
	private int qntCouracados;

    public Jogador(String nome) {
        this.nome = nome;
        this.qntSubmarinos = 0;
        this.qntCruzadores = 0;
        this.qntDestroyers = 0;
        this.qntHidroavioes = 0;
        this.qntCouracados = 0;
    }
    
    public Jogador() {
        this.qntSubmarinos = 0;
        this.qntCruzadores = 0;
        this.qntDestroyers = 0;
        this.qntHidroavioes = 0;
        this.qntCouracados = 0;
    }
    
    public int getQntSubmarinos() {
    	return qntSubmarinos;
    }
    
    public int getQntCruzadores() {
    	return qntCruzadores;
    }
    
    public int getQntDestroyers() {
    	return qntDestroyers;
    }
    
    public int getQntHidroavioes() {
    	return qntHidroavioes;
    }
    
    public int getQntCouracados() {
    	return qntCouracados;
    }
    
    public void addSubmarino() {
    	qntSubmarinos++; 
    }
    
    public void addCruzador() {
    	qntCruzadores++; 
    }
    
    public void addDestroyer() {
    	qntDestroyers++; 
    }
    
    public void addHidroaviao() {
    	qntHidroavioes++; 
    }
    
    public void addCouracado() {
    	qntCouracados++; 
    }
    
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}