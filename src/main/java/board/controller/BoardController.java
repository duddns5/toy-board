package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.dto.Board;
import board.model.service.BoardService;
import common.file.FileDTO;
import common.file.FileUtil;
import common.file.MultiPartParams;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardService();

	public BoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");

		switch (uriArr[uriArr.length - 1]) {
		case "board-list":
			boardList(request, response);
			break;
		case "board-form":
			boardForm(request, response);
			break;
		case "board-upload":
			boardUpload(request, response);
			break;
		default:
		}
	}

	private void boardUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FileUtil util = new FileUtil();
		MultiPartParams params = util.fileUpload(request);
		
		Board board = new Board();
		
		board.setTitle(params.getParameter("bdContent"));
		board.setContent(params.getParameter("bdTitle"));
		board.setUserId(params.getParameter("userId"));
		board.setPassword(params.getParameter("password"));
		List<FileDTO> fileDTOs = params.getFilesInfo();
		boardService.insertBoard(board, fileDTOs);
		response.sendRedirect("/board/board-list");
	}

	private void boardForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/board/board-form").forward(request, response);
	}

	private void boardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Board> boardList = boardService.selectBoardList();
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher("/board/board-list").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
