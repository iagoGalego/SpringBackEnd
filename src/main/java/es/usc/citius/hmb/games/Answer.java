package es.usc.citius.hmb.games;

public class Answer extends es.usc.citius.hmb.model.Sort {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#Answer";
	private Question question;

	public Answer() {
		super();
	}

	public Answer(String uri) {
		super(uri, false);
	}

	public Answer(String uri, boolean b) {
		super(uri, false);
	}

	public void setQuestion(Question value) {
		this.question = value;
	}

	public Question getQuestion() {
		return this.question;
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.question != null) {
            this.question.genURI();
        }

    }
}
