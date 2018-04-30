package es.usc.citius.hmb.games;

public class Question extends es.usc.citius.hmb.model.Sort {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#Question";
	private es.usc.citius.hmb.model.StringType statement;
	private QuestionType questionType;
	private AnswerType answerType;
	private java.util.List<Tag> tags = new java.util.ArrayList<>();

	public Question() {
		super();
	}

	public Question(String uri) {
		super(uri, false);
	}

	public Question(String uri, boolean b) {
		super(uri, false);
	}

	public void setStatement(es.usc.citius.hmb.model.StringType value) {
		this.statement = value;
	}

	public es.usc.citius.hmb.model.StringType getStatement() {
		return this.statement;
	}

	public void setQuestionType(QuestionType value) {
		this.questionType = value;
	}

	public QuestionType getQuestionType() {
		return this.questionType;
	}

	public void setAnswerType(AnswerType value) {
		this.answerType = value;
	}

	public AnswerType getAnswerType() {
		return this.answerType;
	}

	public void setTags(java.util.List<Tag> value) {
		this.tags = value;
	}

	public java.util.List<Tag> getTags() {
		return this.tags;
	}

    public void addTags(Tag value) {
		this.tags.add(value);
	}

    public void removeTags(Tag obj) {
        for (int i = 0; i < this.tags.size(); i++) {
            if (this.tags.get(i).getURI().equals(obj.getURI())) {
                this.tags.remove(i);
                return;
            }
        }
    }

    public void removeTagss() {
		this.tags.clear();
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.statement != null) {
            this.statement.genURI();
        }

        if (this.questionType != null) {
            this.questionType.genURI();
        }

        if (this.answerType != null) {
            this.answerType.genURI();
        }

        if (this.tags != null) {
            for(int i = 0; i < this.tags.size(); i++) {
                Tag o = this.tags.get(i);
                o.genURI();
            }
        }

    }
}
