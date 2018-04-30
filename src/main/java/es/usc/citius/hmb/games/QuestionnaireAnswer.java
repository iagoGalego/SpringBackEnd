package es.usc.citius.hmb.games;

public class QuestionnaireAnswer extends es.usc.citius.hmb.model.Sort {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#QuestionnaireAnswer";
	private java.util.List<Answer> questionAnswers = new java.util.ArrayList<>();

	public QuestionnaireAnswer() {
		super();
	}

	public QuestionnaireAnswer(String uri) {
		super(uri, false);
	}

	public QuestionnaireAnswer(String uri, boolean b) {
		super(uri, false);
	}

	public void setQuestionAnswers(java.util.List<Answer> value) {
		this.questionAnswers = value;
	}

	public java.util.List<Answer> getQuestionAnswers() {
		return this.questionAnswers;
	}

    public void addQuestionAnswers(Answer value) {
		this.questionAnswers.add(value);
	}

    public void removeQuestionAnswers(Answer obj) {
        for (int i = 0; i < this.questionAnswers.size(); i++) {
            if (this.questionAnswers.get(i).getURI().equals(obj.getURI())) {
                this.questionAnswers.remove(i);
                return;
            }
        }
    }

    public void removeQuestionAnswerss() {
		this.questionAnswers.clear();
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.questionAnswers != null) {
            for(int i = 0; i < this.questionAnswers.size(); i++) {
                Answer o = this.questionAnswers.get(i);
                o.genURI();
            }
        }

    }
}
