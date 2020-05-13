import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static List<Usuario> todosUsuarios() {
		List<Usuario> usuarioList = new ArrayList<>();

		try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "root")) {

			String sql = "select login, nome, email from usuario";
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setLogin(rs.getString("login"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				usuarioList.add(u);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível executar o acesso " + e.getMessage());
		}

		return usuarioList;
	}
	
	public static void inserirUsuario (Usuario u) {
		try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "root")) {

			String sql = "insert into usuarios.usuario(login,nome,email) values(?,?,?)";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, u.getLogin());
			stmt.setString(2, u.getNome());
			stmt.setString(3, u.getEmail());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Não foi possível executar o acesso " + e.getMessage());
		}
		
	}
}
