package G21_CENG211_HW1;

import java.util.Random;

public class SalaryManagement {
    private ShopAssistant[] assistants;

    public SalaryManagement(ShopAssistant[] assistants) {
        this.assistants = assistants;
    }

    public void setAssistantsSeniority() {
        Random random = new Random();
        for (int i = 0; i < assistants.length; i++) {
            assistants[i].setSeniority(random.nextInt(15));
        }
    }

    public void setAssistantsSalary() {
        for (int i = 0; i < assistants.length; i++) {
            int seniority = assistants[i].getSeniority();
            double commission = assistants[i].calculateCommission();
            if (seniority < 1) {
                assistants[i].setSalary(1500 + commission);
            }

            else if (seniority >= 1 && seniority < 3) {
                assistants[i].setSalary(2000 + commission);
            }

            else if (seniority >= 3 && seniority < 5) {
                assistants[i].setSalary(2500 + commission);
            }

            else {
                assistants[i].setSalary(3000 + commission);
            }
        }
    }

    public ShopAssistant[] getAssistants() {
        return assistants;
    }

}
