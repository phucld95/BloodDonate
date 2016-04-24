//
//  BDRegister account BDRegisterAccountVC.h
//  BloodDonation
//
//  Created by Le Phuc on 4/24/16.
//  Copyright © 2016 Lê Phúc. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BDRegisterAccountVC : UIViewController <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UIButton *btConfirm;
@property (weak, nonatomic) IBOutlet UITextField *tfUsername;

@property (weak, nonatomic) IBOutlet UITextField *tfPassword;

@property (weak, nonatomic) IBOutlet UITextField *tfConfirmPassword;
@property (weak, nonatomic) IBOutlet UITextField *tfAddress;

@property (weak, nonatomic) IBOutlet UITextField *tfFullname;

@property (weak, nonatomic) IBOutlet UITextField *tfHospital;

@property (weak, nonatomic) IBOutlet UITextField *tfFreetime;





@end
