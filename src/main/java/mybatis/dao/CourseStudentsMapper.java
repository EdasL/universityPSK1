package mybatis.dao;

import java.util.List;
import mybatis.model.CourseStudents;
import org.mybatis.cdi.Mapper;

@Mapper
public interface CourseStudentsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COURSESTUDENTS
     *
     * @mbg.generated Mon May 04 21:50:35 EEST 2020
     */
    int insert(CourseStudents record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COURSESTUDENTS
     *
     * @mbg.generated Mon May 04 21:50:35 EEST 2020
     */
    List<CourseStudents> selectAll();
}