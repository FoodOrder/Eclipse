
package fcu.android.backend.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fcu.android.backend.data.Comment;
import fcu.android.backend.db.CommentDBManager;

@Path("comment/")
public class CommentService {

	private CommentDBManager dbManager = CommentDBManager.getInstance();

	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addComment(Comment comment) {
		
		System.out.println("Comment成功");
		dbManager.addComment(comment);
		return true;
	}

	/*@GET
	@Path("{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Comment getComment(@PathParam("userId") int userId) {
		return dbManager.getComment(userId);
	}*/

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> listComments() {
		return dbManager.listAllComments();
	}
}
