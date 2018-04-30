package es.usc.citius.hmb.games;

public class Questionnaire extends es.usc.citius.hmb.model.Sort {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#Questionnaire";
	private es.usc.citius.hmb.model.StringType name;
	private java.util.List<Question> questions = new java.util.ArrayList<>();
	private java.util.List<Tag> tags = new java.util.ArrayList<>();

	public Questionnaire() {
		super();
	}

	public Questionnaire(String uri) {
		super(uri, false);
	}

	public Questionnaire(String uri, boolean b) {
		super(uri, false);
	}

	public void setName(es.usc.citius.hmb.model.StringType value) {
		this.name = value;
	}

	public es.usc.citius.hmb.model.StringType getName() {
		return this.name;
	}

	public void setQuestions(java.util.List<Question> value) {
		this.questions = value;
	}

	public java.util.List<Question> getQuestions() {
		return this.questions;
	}

    public void addQuestions(Question value) {
		this.questions.add(value);
	}

    public void removeQuestions(Question obj) {
        for (int i = 0; i < this.questions.size(); i++) {
            if (this.questions.get(i).getURI().equals(obj.getURI())) {
                this.questions.remove(i);
                return;
            }
        }
    }

    public void removeQuestionss() {
		this.questions.clear();
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
        
        if (this.name != null) {
            this.name.genURI();
        }

        if (this.questions != null) {
            for(int i = 0; i < this.questions.size(); i++) {
                Question o = this.questions.get(i);
                o.genURI();
            }
        }

        if (this.tags != null) {
            for(int i = 0; i < this.tags.size(); i++) {
                Tag o = this.tags.get(i);
                o.genURI();
            }
        }

    }
}
