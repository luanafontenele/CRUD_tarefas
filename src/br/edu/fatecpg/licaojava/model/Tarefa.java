package br.edu.fatecpg.licaojava.model;

public class Tarefa {
    private int id;
    private String titulo;
    private String categoria;
    private boolean concluida;

    public Tarefa(int id, String titulo, String categoria, boolean concluida) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.concluida = concluida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", concluida=" + concluida +
                '}';
    }
}
