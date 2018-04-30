package es.usc.citius.hmb.games;

public class OptionQuestionType extends AnswerType {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#OptionQuestionType";
	private java.util.List<Option> options = new java.util.ArrayList<>();

	public OptionQuestionType() {
		super();
	}

	public OptionQuestionType(String uri) {
		super(uri, false);
	}

	public OptionQuestionType(String uri, boolean b) {
		super(uri, false);
	}

	public void setOptions(java.util.List<Option> value) {
		this.options = value;
	}

	public java.util.List<Option> getOptions() {
		return this.options;
	}

    public void addOptions(Option value) {
		this.options.add(value);
	}

    public void removeOptions(Option obj) {
        for (int i = 0; i < this.options.size(); i++) {
            if (this.options.get(i).getURI().equals(obj.getURI())) {
                this.options.remove(i);
                return;
            }
        }
    }

    public void removeOptionss() {
		this.options.clear();
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.options != null) {
            for(int i = 0; i < this.options.size(); i++) {
                Option o = this.options.get(i);
                o.genURI();
            }
        }

    }
}
