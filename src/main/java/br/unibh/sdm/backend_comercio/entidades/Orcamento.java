package br.unibh.sdm.backend_comercio.entidades;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "orcamento")
public class Orcamento {
    
    private String id;
    private String nome;
    private String descricao;
    private Double valor;

    public Orcamento() {
        super();
    }

    
    public Orcamento(String id, String nome, String descricao, Double valor) {
        super();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    
    @DynamoDBHashKey
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    

    @DynamoDBAttribute
    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    @DynamoDBAttribute
    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @DynamoDBAttribute
    public Double getValor() {
        return valor;
    }


    public void setValor(Double valor) {
        this.valor = valor;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Orcamento other = (Orcamento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Orcamento [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", valor=" + valor + "]";
    }


   

    
    

   
    

    
    

    
    
}