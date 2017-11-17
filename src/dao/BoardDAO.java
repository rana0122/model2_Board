package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDTO;

public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	// ���ۼ�
	public int insertBoard(BoardDTO board) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String sql;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,"totoro","totoro123");
			
			sql="insert into testboard values(testboard_seq.nextval,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getPasswd());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {	
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(Exception e) {}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(Exception e) {}
			}
			
		}		
		return result;
	}
	
	// �� ������ ���� ���ϱ�
	public int getCount() {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String sql;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "totoro", "totoro123");
			
			sql = "select count(*) from testboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
//				result = rs.getInt("count(*)");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(Exception e) {}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(Exception e) {}
			}
		}
		
		return result;
	}
	
	//�۸��
	public List selectBoard(int start, int end) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		List list = null;
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String sql;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url,"totoro","totoro123");

			sql = "select * from ( select rownum rnum, no, ";
			sql += "writer, passwd, subject, content  from ";
			sql += "(select * from testboard order by no desc)) ";
			sql += "where rnum >= ? and rnum <= ?";			
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs=pstmt.executeQuery();
			
			list = new ArrayList();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setNo(rs.getInt("no"));
				board.setWriter(rs.getString("writer"));
				board.setPasswd(rs.getString("passwd"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				
				list.add(board);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(Exception e) {}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(Exception e) {}
			}
		}
		return list;
	}
	
	
	// �� ������, ������
	public BoardDTO getContent(int no) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BoardDTO board=null;
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String sql;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url,"totoro","totoro123");
			
			sql="select * from testboard  where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				board = new BoardDTO();
//				board.no=5;
				board.setNo(rs.getInt("no"));
				board.setWriter(rs.getString("writer"));
				board.setPasswd(rs.getString("passwd"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));				
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(Exception e) {}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(Exception e) {}
			}
		}
		
		return board;
	}
	
	
	// �ۼ���
	public int update(BoardDTO board) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String sql;
		String rpasswd;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url,"totoro","totoro123");
			
			sql="select * from testboard where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rpasswd = rs.getString("passwd");
				if(rpasswd.equals(board.getPasswd())) {//�����ġ
					sql="update testboard set writer=?, subject=?, content=? where no=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, board.getWriter());
					pstmt.setString(2, board.getSubject());
					pstmt.setString(3, board.getContent());
					pstmt.setInt(4, board.getNo());
					
					result=pstmt.executeUpdate();
					
				}else {	// ����� ��ġ���� �������
					result = -1;
				}		
			
			}			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(Exception e) {}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(Exception e) {}
			}
		}		
		System.out.println("result="+result);
		return result;
	}
	
	
	//�ۻ���
	public int delete(BoardDTO board) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String sql;
		String rpasswd;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url,"totoro","totoro123");
			
			sql="select * from testboard where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rpasswd = rs.getString("passwd");
				if(rpasswd.equals(board.getPasswd())) {//�����ġ
					sql="delete from testboard where no=?";
					pstmt=con.prepareStatement(sql);					
					pstmt.setInt(1, board.getNo());
					
					result=pstmt.executeUpdate();
					
				}else {	// ����� ��ġ���� �������
					result = -1;
				}		
			
			}			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(Exception e) {}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(Exception e) {}
			}
		}		
		return result;
	}	
	
}
