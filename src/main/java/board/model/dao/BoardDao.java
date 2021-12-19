package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.model.dto.Board;
import common.db.JDBCTemplate;
import common.exception.DataAccessException;
import common.file.FileDTO;

public class BoardDao {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();

	public List<Board> selectBoardList(Connection conn, int firstNum, int secondNum) {
		List<Board> boardList = new ArrayList<Board>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select * from (select rownum num, A.* from (select * from board where is_del = 0 order by bd_idx desc) A) where num between ? and ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, firstNum);
			pstm.setInt(2, secondNum);
			rset = pstm.executeQuery();
			while(rset.next()) {
				boardList.add(convertRowToBoardList(rset));
			}
		}catch(SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		return boardList;
	}
	
	public int selectBoardCount(Connection conn) {
		int boardCnt = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String sql = "select count(*) count from board where is_del = 0";
		
		try {
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				boardCnt = rset.getInt("count");
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return boardCnt;
	}

	private Board convertRowToBoardList(ResultSet rset) throws SQLException {
		
		Board board = new Board();
		board.setBdIdx(rset.getInt("bd_idx"));
		board.setUserId(rset.getString("user_id"));
		board.setPassword(rset.getString("password"));
		board.setTitle(rset.getString("title"));
		board.setContent(rset.getString("content"));
		board.setRegDate(rset.getDate("reg_date"));
		board.setViewCount(rset.getInt("view_count"));
		board.setRecCount(rset.getInt("rec_count"));
		board.setIsDel(rset.getInt("is_del"));
		board.setDetailDate(rset.getString("detail_date"));
		return board;
	}

	public void insertBoard(Board board, Connection conn) {
		PreparedStatement pstm = null;
		
		String sql = "insert into board(bd_idx,user_id,password,title,content,reg_date,detail_date) values(sc_board_idx.nextval,?,?,?,?,sysdate,to_char(current_timestamp,'yyyy-mm-dd hh24:mi:ss'))";
		
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

	public void updateViewCount(int bdIdx, Connection conn) {
		PreparedStatement pstm = null;
		String sql = "update board set view_count = (select view_count+1 from board where bd_idx = ?) where bd_idx = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bdIdx);
			pstm.setInt(2, bdIdx);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
	}

	public Board selectBoardDetail(int bdIdx, Connection conn) {
		Board board = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String sql = "select * from board where bd_idx = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bdIdx);
			rset = pstm.executeQuery();
			while(rset.next()) {
				board = convertRowToBoardList(rset);
			}
		} catch(SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
		return board;
	}

	public List<FileDTO> selectFileDTOs(int bdIdx, Connection conn) {
		
		String sql = "select * from file_info where bd_idx = ? and is_del = 0";
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		List<FileDTO> files = new ArrayList<FileDTO>();
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bdIdx);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				files.add(convertRowToFileDTOs(rset));
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return files;
	}

	private FileDTO convertRowToFileDTOs(ResultSet rset) throws SQLException {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setFlIdx(rset.getInt("fl_idx"));
		fileDTO.setBdIdx(rset.getInt("bd_idx"));
		fileDTO.setIsDel(rset.getInt("is_del"));
		fileDTO.setOriginFileName(rset.getString("origin_file_name"));
		fileDTO.setRegDate(rset.getDate("reg_date"));
		fileDTO.setRenameFileName(rset.getString("rename_file_name"));
		fileDTO.setSavePath(rset.getString("save_path"));
		return fileDTO;
	}

	public void updateLikeCount(int bdIdx, Connection conn) {
		PreparedStatement pstm = null;
		String sql = "update board set rec_count = (select rec_count+1 from board where bd_idx = ?) where bd_idx = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bdIdx);
			pstm.setInt(2, bdIdx);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
	}

	public void deleteBoardByBdIdx(int bdIdx, Connection conn) {
		PreparedStatement pstm = null;
		String sql = "update board set is_del = 1 where bd_idx = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bdIdx);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public void deleteFileByBdIdx(int bdIdx, Connection conn) {
		PreparedStatement pstm = null;
		String sql = "update file_info set is_del = 1 where bd_idx = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bdIdx);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public void updateBoard(Board board, Connection conn) {
		PreparedStatement pstm = null;

		String sql = "update board set title = ?, content = ?, reg_date = sysdate, detail_date = to_char(current_timestamp,'yyyy-mm-dd hh24:mi:ss') where bd_idx = ?";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, board.getTitle());
			pstm.setString(2, board.getContent());
			pstm.setInt(3, board.getBdIdx());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public void insertFile(FileDTO fileDTO, Connection conn, int bdIdx) {
		PreparedStatement pstm = null;
		
		String sql = "insert into file_info(fl_idx,bd_idx,origin_file_name,rename_file_name,save_path) values(sc_file_idx.nextval,?,?,?,?)";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bdIdx);
			pstm.setString(2, fileDTO.getOriginFileName());
			pstm.setString(3, fileDTO.getRenameFileName());
			pstm.setString(4, fileDTO.getSavePath());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	

}
