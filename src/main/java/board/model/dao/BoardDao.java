package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import board.model.dto.Board;
import common.db.JDBCTemplate;
import common.exception.DataAccessException;
import common.file.FileDTO;

public class BoardDao {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();

	public List<Board> selectBoardList(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertBoard(Board board, Connection conn) {
		PreparedStatement pstm = null;
		
		String sql = "insert into board(bd_idx,user_id,password,title,content,reg_date) values(sc_board_idx.nextval,?,?,?,?,to_char(current_timestamp,'yyyy-mm-dd hh24:mi:ss'))";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, board.getUserId());
			pstm.setString(2, board.getPassword());
			pstm.setString(3, board.getTitle());
			pstm.setString(4, board.getContent());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public void insertFile(FileDTO fileDTO, Connection conn) {
		PreparedStatement pstm = null;
		
		String sql = "insert into file_info(fl_idx,bd_idx,origin_file_name,rename_file_name,save_path) values(sc_file_idx.nextval,sc_board_idx.currval,?,?,?)";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, fileDTO.getOriginFileName());
			pstm.setString(2, fileDTO.getRenameFileName());
			pstm.setString(3, fileDTO.getSavePath());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

}
