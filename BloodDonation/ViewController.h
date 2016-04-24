//
//  ViewController.h
//  BloodDonation
//
//  Created by Le Phuc on 4/24/16.
//  Copyright © 2016 Lê Phúc. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BaseViewController.h"

@interface ViewController : BaseViewController <UITextFieldDelegate>
@property (weak, nonatomic) IBOutlet UIButton *btLogin;
@property (weak, nonatomic) IBOutlet UIButton *btFacebookLogin;
@property (weak, nonatomic) IBOutlet UIButton *btTwiter;
@property (weak, nonatomic) IBOutlet UIButton *btGoogle;

@property (weak, nonatomic) IBOutlet UIButton *btRegister;
@property (weak, nonatomic) IBOutlet UITextField *tfUsermane;
@property (weak, nonatomic) IBOutlet UITextField *tfPassword;

@end

