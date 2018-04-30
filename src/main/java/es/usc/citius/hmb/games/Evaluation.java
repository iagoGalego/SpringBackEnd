package es.usc.citius.hmb.games;

public class Evaluation extends es.usc.citius.hmb.model.Sort {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#Evaluation";
	private Question question;
	private Answer answer;
	private es.usc.citius.hmb.model.BooleanType value;

	public Evaluation() {
		super();
	}

	public Evaluation(String uri) {
		super(uri, false);
	}

	public Evaluation(String uri, boolean b) {
		super(uri, false);
	}

	public void setQuestion(Question value) {
		this.question = value;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setAnswer(Answer value) {
		this.answer = value;
	}

	public Answer getAnswer() {
		return this.answer;
	}

	public void setValue(es.usc.citius.hmb.model.BooleanType value) {
		this.value = value;
	}

	public es.usc.citius.hmb.model.BooleanType getValue() {
		return this.value;
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.question != null) {
            this.question.genURI();
        }

        if (this.answer != null) {
            this.answer.genURI();
        }

        if (this.value != null) {
            this.value.genURI();
        }

    }
}
