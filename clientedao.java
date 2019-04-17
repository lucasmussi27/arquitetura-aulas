import java.sql.Connection;
import java.sql.DriverManager;
import Cliente;

public class ClienteDAO {
	private Connection conn;

	public ClienteDAO() {
		this.conn = 
DriverManager.getConnection("jdbc:derby:memory:database;create=true");
	}

	public void create(Cliente cli) {
		String sql = "insert into cliente values(?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement();
			stmt.executeQuery(sql);
			stmt.setInt(1, stmt.getGeneratedKeys());
			stmt.setString(2, cli.getNome());
			stmt.setInt(3, cli.getIdade());
			stmt.setString(4, cli.getTelefone());
			stmt.setDouble(5, cli.getCredito());
			stmt.setString(6, cli.getPais().getNome());
			stmt.executeUpdate();
		} catch(SQLException se) {
			System.out.println(se.getCause());
			System.out.println(se.getMessage());
		}
	}

	public List<Cliente> read() {
		String sql = "select * from cliente";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Cliente> lista = new ArrayList<>();
		try {
			stmt = this.conn.prepareStatement();
			stmt.executeQuery(sql);
			rs = stmt.execute();
			while(rs.next()) {
				Cliente cli = new Cliente();
				Pais pais = new Pais();
				cli.setId(rs.getInt('id'));
				cli.setNome(rs.getString('nome'));
				cli.setIdade(rs.getInt('idade'));
				cli.setTelefone(rs.getString('telefone'));
				cli.setCredito(rs.getDouble('credito'));
				pais.setNome(rs.getString('pais'));
				cli.setPais(pais);
				lista.add(cli);
			}
		} catch(SQLException se) {
			System.out.println(se.getCause());
			System.out.println(se.getMessage());
		}
		return lista;
	}

	public void update(Cliente cli) {
		String sql = "update cliente set nome = ?, idade = ?,"
				+ "telefone = ?, credito = ?, pais = ?"
				+ "where id = ?";
		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement();
			stmt.executeQuery(sql);
			stmt.setInt(1, cli.getId());
			stmt.setString(2, cli.getNome());
			stmt.setInt(3, cli.getIdade());
			stmt.setString(4, cli.getTelefone());
			stmt.setDouble(5, cli.getCredito());
			stmt.setString(6, cli.getPais().getNome());
			stmt.executeUpdate();
		} catch(SQLException se) {
			System.out.println(se.getCause());
			System.out.println(se.getMessage());
		}
	}

	public void delete(int id) {
		String sql = "delete from cliente where id = ?";
		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement();
			stmt.executeQuery(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch(SQLException se) {
			System.out.println(se.getCause());
			System.out.println(se.getMessage());
		}
	}
}
