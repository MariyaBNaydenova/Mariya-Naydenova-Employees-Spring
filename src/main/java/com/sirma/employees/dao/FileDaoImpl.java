package com.sirma.employees.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.sirma.employees.model.ProjectBean;

@Repository
public class FileDaoImpl implements FileDao {
	
	private final String ERROR_FILE_EMPTY = "File is empty or no file was uploaded.";
	private final String ERROR_ONE_EMPLOYEE = "File must contain at least 2 emloyees.";
	private final String ERROR_WRONG_FORMAT = "File content is not formatted as expected.";
	private final String CORRECT_FILE = "OK";

	@Override
	public String checkErrors(MultipartFile file) {

		if(file.isEmpty()) {
			return ERROR_FILE_EMPTY;			
		} else {
			List<String> lines = getFileContent(file);
			HashMap<Integer, List<ProjectBean>> employees = readEmployeesFromFile(lines);
			if(employees != null) { 
				if(employees.size() < 2) {
					return ERROR_ONE_EMPLOYEE;
				} else {
					return CORRECT_FILE;
				}
			} else {
				return ERROR_WRONG_FORMAT;
			}	
		}
	}
	
	@Override
	public List<String[]> getDataGridWithCommonProjects(MultipartFile file) {
		List<String> rows = getFileContent(file);
		HashMap<Integer, List<ProjectBean>> employees = readEmployeesFromFile(rows);
		String[] result = findResult(employees).split(", ");
		
		Integer firstEmployeeID = Integer.valueOf(result[0]);
		Integer secondEmployeeID = Integer.valueOf(result[1]);
		
		List<ProjectBean> firstEmployeeProjects = employees.get(firstEmployeeID);
		List<ProjectBean> secondEmployeeProjects = employees.get(secondEmployeeID);

		List<String[]> grid = new ArrayList<>();
		
        Set<Integer> commonProjects = new HashSet<>();
        for(ProjectBean p1 : firstEmployeeProjects) {
            for(ProjectBean p2 : secondEmployeeProjects) {
                if(p1.getProjectId() == p2.getProjectId() && !commonProjects.contains(p1.getProjectId())) {
                	
                    long daysTogetherOnProject = daysTogetherOnProject(p1, p2);
                    commonProjects.add(p1.getProjectId());
                    
                    String[] line = {Integer.toString(firstEmployeeID), 
                    				 Integer.toString(secondEmployeeID), 
                    				 Integer.toString(p1.getProjectId()), 
                    				 Long.toString(daysTogetherOnProject)};
                    grid.add(line);
                }
            }
        }
		return grid;
	}
	
	
	private List<String> getFileContent(MultipartFile file) {
		BufferedReader reader;
		List<String> lines = new ArrayList<>();
		InputStream is = null;
		try {
		     String line;
		     is = file.getInputStream();
		     reader = new BufferedReader(new InputStreamReader(is));
		     while ((line = reader.readLine()) != null) {
		    	 lines.add(line);
		     }
		     is.close();
		  } catch (IOException e) {
		    System.err.println(e.getMessage());       
		  }
		return lines;
	}
	
    public HashMap<Integer, List<ProjectBean>> readEmployeesFromFile(List<String> lines) {
    	
        HashMap<Integer, List<ProjectBean>> employees = new HashMap<>();
        try {
            for (String line : lines) {
                String[] data = line.split(", ");
                Integer employeeId = Integer.valueOf(data[0]);
                ProjectBean project = createProject(data);
                setProjectToEmployee(employeeId, project, employees);
            }
        } catch (Exception e) {
        	return null;
        }
        return employees;
    }
    
    private ProjectBean createProject(String[] data) {
    	ProjectBean project = new ProjectBean();
    	
        project.setProjectId(Integer.parseInt(data[1]));
        project.setFromDate(convertStringToDate(data[2]));
        if(data[3].equals("NULL")) project.setToDate(LocalDate.now().atStartOfDay());
        else project.setToDate(convertStringToDate(data[3]));
        
        return project;
    }
   
    private void setProjectToEmployee(Integer employeeId, ProjectBean project,  HashMap<Integer, List<ProjectBean>> employees) {
        if(employees.get(employeeId) != null) {
            List<ProjectBean> projects = employees.get(employeeId);
            projects.add(project);
        } else {
            List<ProjectBean> projects = new ArrayList<>();
            projects.add(project);
            employees.put(employeeId, projects);
        }
    }
    
    private LocalDateTime convertStringToDate(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, dtf);
        return localDate.atStartOfDay();
    }

	private String findResult(HashMap<Integer, List<ProjectBean>> employees) {
	    List<Integer> employeeIds = new ArrayList<>();
	    employeeIds.addAll(employees.keySet());
	
	    Integer firstEmployee = null;
	    Integer secondEmployee = null;
	    long daysTogether = 0;
	
	    for(int i = 0; i < employeeIds.size()-1; i++) {
	        for(int j = i+1; j < employeeIds.size(); j++) {
	            long days = findNumberOfDaysTogether(employees.get(employeeIds.get(i)), employees.get(employeeIds.get(j)));
	
	            if(days > daysTogether) {
	                firstEmployee = employeeIds.get(i);
	                secondEmployee = employeeIds.get(j);
	                daysTogether = days;
	            }
	        }
	    }
	
		if(daysTogether == 0) return "No employees worked together on any project.";
		return firstEmployee.intValue() + ", " + secondEmployee.intValue() + ", " + daysTogether;
	}

	private long findNumberOfDaysTogether(List<ProjectBean> firstEmployeeProjects, List<ProjectBean> secondEmployeeProjects) {
        long daysTogether = 0;
        Set<Integer> commonProjects = new HashSet<>();
        for(ProjectBean p1 : firstEmployeeProjects) {
            for(ProjectBean p2 : secondEmployeeProjects) {
                if(p1.getProjectId() == p2.getProjectId() && !commonProjects.contains(p1.getProjectId())) {
                    daysTogether += daysTogetherOnProject(p1, p2);
                    commonProjects.add(p1.getProjectId());
                }
            }
        }
        return daysTogether;
	}
   
    private long daysTogetherOnProject(ProjectBean p1, ProjectBean p2) {
        LocalDateTime startDate;
        LocalDateTime endDate;

        if(p1.getFromDate().isAfter(p2.getFromDate())) startDate = p1.getFromDate();
        else startDate = p2.getFromDate();

        if(p1.getToDate().isBefore(p2.getToDate())) endDate = p1.getToDate();
        else endDate = p2.getToDate();

        if(startDate.isBefore(endDate))
            return ChronoUnit.DAYS.between(startDate, endDate);
        return 0;
    }

}
