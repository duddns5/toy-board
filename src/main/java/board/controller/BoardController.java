package board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.dto.Board;
import board.model.service.BoardService;
import common.code.ErrorCode;
import common.exception.HandlableException;
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
		case "board-detail":
			boardDetail(request, response);
			break;
		case "board-like":
			boardLike(request, response);
			break;
		case "board-delete":
			boardDelete(request, response);
			break;
		case "board-modify":
			boardModify(request, response);
			break;
		case "board-update":
			boardUpdate(request, response);
			break;
		default:
		}
	}

	private void boardUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUtil util = new FileUtil();
		MultiPartParams params = util.fileUpload(request);
		Board board = new Board();
		int bdIdx = Integer.parseInt(params.getParameter("bdIdx"));
		Board boardInfo = boardService.selectBoardByBdIdx(bdIdx);
		if(params.getParameter("password").equals(boardInfo.getPassword())) {
			board.setBdIdx(bdIdx);
			board.setTitle(params.getParameter("title"));
			board.setContent(params.getParameter("content"));
			List<FileDTO> fileDTOs = params.getFilesInfo();
			boardService.updateBoardByBdIdx(board,fileDTOs);
			response.sendRedirect("/board/board-detail?bdIdx="+bdIdx);
		}else {
			throw new HandlableException(ErrorCode.FAILED_BOARD_DELETE_ERROR);
		}
		
	}

	private void boardModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		Board board = boardService.selectBoardByBdIdx(bdIdx);
		request.setAttribute("board", board);
		request.getRequestDispatcher("/board/board-modify").forward(request, response);
	}

	private void boardDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		Board board = boardService.selectBoardByBdIdx(bdIdx);
		if(request.getParameter("password").equals(board.getPassword())) {
			boardService.deleteBoardByBdIdx(bdIdx);
			response.sendRedirect("/board/board-list");
		}else {
			throw new HandlableException(ErrorCode.FAILED_BOARD_DELETE_ERROR);
		}
	}

	private void boardLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		boardService.updateLikeCount(bdIdx);
		response.sendRedirect("/board/board-detail?bdIdx="+bdIdx);
	}

	private void boardDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		boardService.updateViewCount(bdIdx);
		Map<String, Object> datas = boardService.selectBoardDetail(bdIdx);
		request.setAttribute("datas", datas);
		request.getRequestDispatcher("/board/board-detail").forward(request, response);
	}

	private void boardUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FileUtil util = new FileUtil();
		MultiPartParams params = util.fileUpload(request);
		
		Board board = new Board();
		
		board.setTitle(params.getParameter("content"));
		board.setContent(params.getParameter("title"));
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
		
		String page_ = request.getParameter("page");
		int page = 1;
		if(page_ != null && !page_.equals("")) {
			page = Integer.parseInt(page_);
		}
		Map<String,Object> boardList = boardService.selectBoardList(page);
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher("/board/board-list").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
