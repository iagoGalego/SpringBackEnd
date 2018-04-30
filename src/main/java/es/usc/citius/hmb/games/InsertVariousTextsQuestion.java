package es.usc.citius.hmb.games;

public class InsertVariousTextsQuestion extends TextQuestionType {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#InsertVariousTextsQuestion";
	private java.util.List<TextSolution> solutions = new java.util.ArrayList<>();

	public InsertVariousTextsQuestion() {
		super();
	}

	public InsertVariousTextsQuestion(String uri) {
		super(uri, false);
	}

	public InsertVariousTextsQuestion(String uri, boolean b) {
		super(uri, false);
	}

	public void setSolutions(java.util.List<TextSolution> value) {
		this.solutions = value;
	}

	public java.util.List<TextSolution> getSolutions() {
		return this.solutions;
	}

    public void addSolutions(TextSolution value) {
		this.solutions.add(value);
	}

    public void removeSolutions(TextSolution obj) {
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
                TextSolution o = this.solutions.get(i);
                o.genURI();
            }
        }

    }
}
