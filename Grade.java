public class Grade {
    private Double homeworkGrade;
    private Double midtermGrade;
    private Double finalGrade;

    public Grade(){
        homeworkGrade = 0.0;
        midtermGrade = 0.0;
        finalGrade = 0.0;
    }
    public Grade(Double homeworkGrade, Double midtermGrade, Double finalGrade) {
        this.homeworkGrade = homeworkGrade;
        this.midtermGrade = midtermGrade;
        this.finalGrade = finalGrade;
    }

    public Double getHomeworkGrade() {
        return homeworkGrade;
    }

    public Double getMidtermGrade() {
        return midtermGrade;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setGradeByType(AssignmentType type, Double grade){
        switch (type){
            case HOMEWORK:
                homeworkGrade = grade;
                break;
            case MIDTERM:
                midtermGrade = grade;
                break;
            case FINAL:
                finalGrade = grade;
                break;
        }
    }
    public Double getGradeByType(AssignmentType type){
        return switch (type) {
            case HOMEWORK -> homeworkGrade;
            case MIDTERM -> midtermGrade;
            case FINAL -> finalGrade;
        };
    }
}
