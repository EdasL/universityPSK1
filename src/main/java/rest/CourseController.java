package rest;

import entities.Course;
import lombok.Getter;
import lombok.Setter;
import persistence.CoursesDAO;
import rest.contracts.CourseDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/courses")
public class CourseController {

    @Inject
    @Setter
    @Getter
    private CoursesDAO coursesDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Course course = coursesDAO.findOne(id);
        if (course == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CourseDto courseDto = new CourseDto();
        courseDto.setName(course.getName());

        return Response.ok(courseDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer id, CourseDto courseDto) {
        try {
            Course course = coursesDAO.findOne(id);
            if (course == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            course.setName(courseDto.getName());

            coursesDAO.update(course);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(CourseDto courseDto) {
        try {
            Course course = new Course();
            course.setName(courseDto.getName());

            coursesDAO.persist(course);

            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}