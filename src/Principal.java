import java.util.List;

public class Principal {

	public static void main(String[] args) {
		List<Usuario> lista = UsuarioDAO.todosUsuarios();
		lista.forEach(System.out::println);

	}

}
