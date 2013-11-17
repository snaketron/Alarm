package struct.core.constants;

public interface CoreConst {
	final int CHECK_PERIOD_MILLI = 3000;
	
	enum AlarmType {
		ATOMIC("atomic"), 
		RECURRING("recurring)");
		
		private String type;
		private AlarmType(String type) {
			this.type = type;
		}
		
		public String getType() {
			return type;
		}
		
		public void setType(String type) {
			this.type = type;
		}
		
		public static AlarmType find(String type) {
			switch (type) {
			case "atomic":
				return ATOMIC;
			case "recurring":
				return RECURRING;
			}
			return null;
		}
	}
}
