package br.edu.fatecpg.licaojava.view;

import br.edu.fatecpg.licaojava.bd.DB;
import br.edu.fatecpg.licaojava.model.Tarefa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTarefa {
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int op;

        do {
            System.out.println("Menu:\n 1 - Criar \n 2 - Visualizar\n 3 - Editar \n 4 - Excluir \n 5 - Categorias\n 6 - Concluída \n 7 - Sair\n");
            op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1:
                    System.out.println("Tarefa: ");
                    String titulo = scan.nextLine();

                    System.out.println("Categoria: ");
                    String categoria = scan.nextLine();

                    try (var conn = DB.connection()) {
                        System.out.println("Conexão realizada com sucesso!");

                        var query = "INSERT INTO tb_tarefa(titulo, categoria) VALUES (?,?)";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, titulo);
                        stmt.setString(2, categoria);
                        stmt.execute();

                        System.out.println("Tarefa criada com sucesso!");
                    } catch (SQLException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 2:
                    try (var conn = DB.connection()){
                        System.out.println("Conexão realizada com sucesso!");
                        List<Tarefa> tarefas = new ArrayList<>();
                        var consulta = "SELECT * FROM tb_tarefa";
                        PreparedStatement stmtConsulta = conn.prepareStatement(consulta);
                        ResultSet rs = stmtConsulta.executeQuery();
                        while(rs.next()){
                            tarefas.add(new Tarefa(
                                    rs.getInt("id"),
                                    rs.getString("titulo"),
                                    rs.getString("categoria"),
                                    rs.getBoolean("concluida")
                            ));
                        }
                        tarefas.forEach(System.out::println);
                    }
                    catch(SQLException e){
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("titulo: ");
                    String Novotitulo = scan.nextLine();
                    System.out.println("id: ");
                    int Novoid = scan.nextInt();
                    try(var conn = DB.connection()){
                    System.out.println("Conexão realizada com sucesso!");
                    var query = "UPDATE tb_tarefa SET titulo = ? WHERE id = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, Novotitulo);
                    stmt.setInt(2, Novoid);
                    stmt.execute();

                    System.out.println("Tarefa editada com sucesso!");
                }
                    catch(SQLException e){
                    System.out.println("Erro: " + e.getMessage());
                }
                    break;

                case 4:
                    System.out.println("Digite o id: ");
                    int Exid = scan.nextInt();
                    try(var conn = DB.connection()){
                        var query = "DELETE from tb_tarefa WHERE id = ?";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setInt(1, Exid);
                        stmt.execute();
                    System.out.println("Tarefa excluída!");
                }
                    catch(SQLException e){
                    System.out.println("Erro: " + e.getMessage());
                }
                    break;

                case 5:
                    System.out.println("Qual categoria deseja ver? ");
                    String Dcategoria = scan.nextLine();
                    try(var conn = DB.connection()){
                        var query = "SELECT * FROM tb_tarefa WHERE categoria = ?";
                        var stmt = conn.prepareStatement(query);
                        stmt.setString(1, Dcategoria);
                        var rs = stmt.executeQuery();

                        List<Tarefa> filtradas = new ArrayList<>(); // Cria a lista aqui!
                        while(rs.next()) {
                            filtradas.add(new Tarefa(
                                    rs.getInt("id"),
                                    rs.getString("titulo"),
                                    rs.getString("categoria"),
                                    rs.getBoolean("concluida")
                            ));
                        }
                        filtradas.forEach(System.out::println); // Mostra o resultado
                    } catch(SQLException e){
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("ID da tarefa que você terminou: ");
                    int idPronta = scan.nextInt();

                    try (var conn = DB.connection()) {
                        var query = "UPDATE tb_tarefa SET concluida = true WHERE id = ?";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setInt(1, idPronta);
                        stmt.execute();

                        System.out.println("Tarefa marcada como concluída!");
                    } catch (SQLException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");

            }

        }
        while(op != 7);
        scan.close();
    }
}
