package es.usc.citius.hmb.games;

public class MultipleFillInTheBlanksQuestionAnswer extends Answer {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#MultipleFillInTheBlanksQuestionAnswer";
	private java.util.List<es.usc.citius.hmb.model.StringType> solutions = new java.util.ArrayList<>();

	public MultipleFillInTheBlanksQuestionAnswer() {
		super();
	}

	public MultipleFillInTheBlanksQuestionAnswer(String uri) {
		super(uri, false);
	}

	public MultipleFillInTheBlanksQuestionAnswer(String uri, boolean b) {
		super(uri, false);
	}

	public void setSolutions(java.util.List<es.usc.citius.hmb.model.StringType> value) {
		this.solutions = value;
	}

	public java.util.List<es.usc.citius.hmb.model.StringType> getSolutions() {
		return this.solutions;
	}

    public void addSolutions(es.usc.citius.hmb.model.StringType value) {
		this.solutions.add(value);
	}

    public void removeSolutions(es.usc.citius.hmb.model.StringType obj) {
        for (int i = 0; i < this.solutions.size(); i++) {
            if (this.solutions.get(i).getURI().equals(obj.getURI())) {
                this.solutions.remove(i);
                return;
            }
        }
    }

    public void removeSolutionss() {
		this.solutions.clear();
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.solutions != null) {
            for(int i = 0; i < this.solutions.size(); i++) {
                es.usc.citius.hmb.model.StringType o = this.solutions.get(i);
                o.genURI();
            }
        }

    }
}
