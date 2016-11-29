package file_analyzer;

public class FileAnalyzerStringPairs {
	private String _pair = "";
	private double _sumOccurrences;
	
	public FileAnalyzerStringPairs(String pair, double sumOccurrences) {
		_pair = pair;
		_sumOccurrences = sumOccurrences;
	}
	
	public String getPair() {
		return _pair;
	}
	
	public double getSumOccurrences() {
		return _sumOccurrences;
	}
	
	@Override
	public String toString() {
		return "[\""+_pair+"\","+_sumOccurrences+"]";
	}
}
