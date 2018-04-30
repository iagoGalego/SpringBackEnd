package es.usc.citius.hmb.games;

public class QuestionnaireEvaluation extends Evaluation {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#QuestionnaireEvaluation";
	private java.util.List<Evaluation> questionEvaluations = new java.util.ArrayList<>();

	public QuestionnaireEvaluation() {
		super();
	}

	public QuestionnaireEvaluation(String uri) {
		super(uri, false);
	}

	public QuestionnaireEvaluation(String uri, boolean b) {
		super(uri, false);
	}

	public void setQuestionEvaluations(java.util.List<Evaluation> value) {
		this.questionEvaluations = value;
	}

	public java.util.List<Evaluation> getQuestionEvaluations() {
		return this.questionEvaluations;
	}

    public void addQuestionEvaluations(Evaluation value) {
		this.questionEvaluations.add(value);
	}

    public void removeQuestionEvaluations(Evaluation obj) {
        for (int i = 0; i < this.questionEvaluations.size(); i++) {
            if (this.questionEvaluations.get(i).getURI().equals(obj.getURI())) {
                this.questionEvaluations.remove(i);
                return;
            }
        }
    }

    public void removeQuestionEvaluationss() {
		this.questionEvaluations.clear();
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.questionEvaluations != null) {
            for(int i = 0; i < this.questionEvaluations.size(); i++) {
                Evaluation o = this.questionEvaluations.get(i);
                o.genURI();
            }
        }

    }
}
