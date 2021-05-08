
public class IssueStage {
	public static void issueStage() {
		if(!InstructionMemory.fetch.isEmpty()) {
		String ins = InstructionMemory.fetch.get(0);
		
		String[] splittedI = ins.split(" ");
		String op = splittedI[0];
		String outputRegister = splittedI[1];
		String input1Register = splittedI[2];
		String input2Register="";
//		if(splittedI[3]!=null)
//		input2Register = splittedI[3];
		
		switch (op) {
		case "ADD":
		case "SUB":
			input2Register = splittedI[3];
			boolean flag = false;
			AdderBuffer temp = new AdderBuffer();
			for (int i = 0; i < InstructionMemory.adderBuffer.length; i++) {
				if (InstructionMemory.adderBuffer[i] == null && flag == false) {
					flag=true;
					temp.name = "A" + (i + 1);
					temp.op = splittedI[0];
					temp.inst=ins;
					InstructionMemory.fetch.remove(0);
					InstructionMemory.issue.add(ins);
					System.out.println("The instruction " + ins + " was issued at cycle " + InstructionMemory.cycle );
					for (int j = 0; j < InstructionMemory.register.length; j++) {
						if(InstructionMemory.register[j].rname.equals(input1Register)) {
							if(InstructionMemory.register[j].qi==null||InstructionMemory.register[j].qi.equals("0")) {
								//law rabna karmna we el register fy el value
							
								temp.vj=input1Register;
							}
							else {// da law el register lesa mestany value
								temp.qj=InstructionMemory.register[j].qi;
							}
						}
						if(InstructionMemory.register[j].rname.equals(input2Register)) {
							if(InstructionMemory.register[j].qi==null||InstructionMemory.register[j].qi.equals("0") ) {
								//System.out.println("hyyy");
								//law rabna karmna we el register fy el value
								temp.vk=input2Register;
							}
							else {// da law el register lesa mestany value
								temp.qk=InstructionMemory.register[j].qi;
							}
						}	
					}
					InstructionMemory.adderBuffer[i]=temp;
				}

			} // END OF FOR LOOP
			for (int i = 0; i < InstructionMemory.register.length; i++) {
				if(InstructionMemory.register[i].rname.equals(outputRegister)) {
					InstructionMemory.register[i].qi=temp.name;
				}
				
			}
			break;
			
			
			
		case "MUL":
		case "DIV":
			input2Register = splittedI[3];
			boolean flag2 = false;
			MultiplierBuffer temp2 = new MultiplierBuffer();
			for (int i = 0; i < InstructionMemory.multiplierBuffer.length; i++) {
				if (InstructionMemory.multiplierBuffer[i] == null && flag2 == false) {
					InstructionMemory.fetch.remove(0);
					InstructionMemory.issue.add(ins);
					System.out.println("The instruction " + ins + " was issued at cycle " + InstructionMemory.cycle );
					flag2=true;
					temp2.name = "M" + (i + 1);
					temp2.op = splittedI[0];
					temp2.inst=ins;
					for (int j = 0; j < InstructionMemory.register.length; j++) {
						if(InstructionMemory.register[j].rname.equals(input1Register)) {
							//System.out.println(InstructionMemory.register[j].qi);
							if( InstructionMemory.register[j].qi==null || InstructionMemory.register[j].qi.equals("0") ) {
								//law rabna karmna we el register fy el value
								temp2.vj=input1Register;
							}
							else {// da law el register lesa mestany value
								temp2.qj=InstructionMemory.register[j].qi;
							}
						}
						if(InstructionMemory.register[j].rname.equals(input2Register)) {
							if(  InstructionMemory.register[j].qi==null ||InstructionMemory.register[j].qi.equals("0")) {
								//law rabna karmna we el register fy el value
								temp2.vk=input2Register;
							}
							else {// da law el register lesa mestany value
								temp2.qk=InstructionMemory.register[j].qi;
							}
						}	
					}
					//System.out.println("da5alt el buffer");
					InstructionMemory.multiplierBuffer[i]=temp2;
				}

			} // END OF FOR LOOP
			for (int i = 0; i < InstructionMemory.register.length; i++) {
				if(InstructionMemory.register[i].rname.equals(outputRegister)) {
					//System.out.println("hiii"+ temp2.name);
					InstructionMemory.register[i].qi=temp2.name;
				}
				
			}
			break;
			
		case "SD":// outputregister=register , //input1=address
			boolean flag3 = false;
			StoreBuffer temp3 = new StoreBuffer();
			for (int i = 0; i < InstructionMemory.storeBuffer.length; i++) {
				if (InstructionMemory.storeBuffer[i] == null && flag3 == false) {
					InstructionMemory.fetch.remove(0);
					InstructionMemory.issue.add(ins);
					System.out.println("The instruction " + ins + " was issued at cycle " + InstructionMemory.cycle );
					flag3=true;
					temp3.name = "S" + (i + 1);
					temp3.inst=ins;
					for (int j = 0; j < InstructionMemory.register.length; j++) {
						if(outputRegister==InstructionMemory.register[j].rname) {
							if(InstructionMemory.register[j].qi=="0" || InstructionMemory.register[j].qi==null) {
								//law rabna karmna we el register fy el value
								temp3.vj=outputRegister;
							}
							else {// da law el register lesa mestany value
								temp3.qj=InstructionMemory.register[j].qi;
							}
						}
						}	
					temp3.address=input1Register;
					
					InstructionMemory.storeBuffer[i]=temp3;
				}

			} // END OF FOR LOOP
			break;
			
		case "LD":// outputregister=register , //input1=address
			boolean flag4 = false;
			LoadBuffer temp4 = new LoadBuffer();
			for (int i = 0; i < InstructionMemory.loadBuffer.length; i++) {
				if (InstructionMemory.loadBuffer[i] == null && flag4 == false) {
					InstructionMemory.fetch.remove(0);
					InstructionMemory.issue.add(ins);
					System.out.println("The instruction " + ins + " was issued at cycle " + InstructionMemory.cycle );
					flag4=true;
					temp4.name = "L" + (i + 1);
					temp4.address=input1Register;
					temp4.inst=ins;
					InstructionMemory.loadBuffer[i]=temp4;
				}

			} // END OF FOR LOOP
			for (int i = 0; i < InstructionMemory.register.length; i++) {
				if(InstructionMemory.register[i].rname.equals(outputRegister)) {
					InstructionMemory.register[i].qi=temp4.name;
				}
				
			}
			break;
		
	}
	}
}

}
