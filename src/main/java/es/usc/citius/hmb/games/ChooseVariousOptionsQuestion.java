package es.usc.citius.hmb.games;

public class ChooseVariousOptionsQuestion extends OptionQuestionType {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#ChooseVariousOptionsQuestion";
	private java.util.List<Option> solutions = new java.util.ArrayList<>();

	public ChooseVariousOptionsQuestion() {
		super();
	}

	public ChooseVariousOptionsQuestion(String uri) {
		super(uri, false);
	}

	public ChooseVariousOptionsQuestion(String uri, boolean b) {
		super(uri, false);
	}

	public void setSolutions(java.util.List<Option> value) {
		this.solutions = value;
	}

	public java.util.List<Option> getSolutions() {
		return this.solutions;
	}

    public void addSolutions(Option value) {
		this.solutions.add(value);
	}

    public void removeSolutions(Option obj) {
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
                Option o = this.solutions.get(i);
                o.genURI();
            }
        }

    }
}
