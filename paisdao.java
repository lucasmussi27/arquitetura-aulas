import java.sql.Connection;
import java.sql.DriverManager;
import Pais;

public class PaisDAO {
	private Connection conn;

	public PaisDAO() {
		this.conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true");
	}

	public void create(Pais p) {
		String sql = "insert into pais values (?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement();
			stmt.executeQuery(sql);
			stmt.setInt(1, stmt.getGeneratedKeys());
			stmt.setString(2, p.getNome());
			stmt.setString(3, p.getSigla());
			stmt.setInt(4, p.getCodigo()));
			stmt.executeUpdate();
		} catch(SQLException se) {
			System.out.println(se.getCause());
			System.out.println(se.getMessage());
		}
	}

	public List<Cliente> read() {
		String sql = "select * from pais";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Pais> lista = new ArrayList<>();
		try {
			stmt = this.conn.prepareStatement();
			stmt.executeQuery(sql);
			rs = stmt.execute();
			while(rs.next()) {
				Pais pais = new Pais();
				p.setId(rs.getInt('id'));
				p.setNome(rs.getString('nome'));
				p.setSigla(rs.getString('sigla'));
				p.setCodigo(rs.getInt('codigo_telefone'));
				lista.add(p);
			}
		} catch(SQLException se) {
			System.out.println(se.getCause());
			System.out.println(se.getMessage());
		}
		return lista;
	}

	public void update(Pais p) {
		String sql = "update pais set nome = ?, sigla = ?,"
				+ "codigo = ? where id = ?";
		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement();
			stmt.executeQuery(sql);
			stmt.setInt(1, cli.getId());
			stmt.setString(2, p.getNome());
			stmt.setString(3, p.getSigla());
			stmt.setInt(4, p.getCodigo()));
			stmt.executeUpdate();
		} catch(SQLException se) {
			System.out.println(se.getCause());
			System.out.println(se.getMessage());
		}
	}

	public void delete(int id) {
		String sql = "delete from pais where id = ?";
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