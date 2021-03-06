//
//  BDRegister account BDRegisterAccountVC.m
//  BloodDonation
//
//  Created by Le Phuc on 4/24/16.
//  Copyright © 2016 Lê Phúc. All rights reserved.
//

#import "BDRegisterAccountVC.h"
#import "BDRegissterCell.h"


@interface BDRegisterAccountVC ()

@end

@implementation BDRegisterAccountVC

- (void)viewDidLoad {
    [super viewDidLoad];
    _btConfirm.layer.cornerRadius = 5;
    _btConfirm.layer.masksToBounds = YES;
    UIImage* imageGlobal = [UIImage imageNamed:@"home"];
    CGRect frameimg = CGRectMake(0, 0, imageGlobal.size.width, imageGlobal.size.height);
    UIButton *Logout = [[UIButton alloc] initWithFrame:frameimg];
    [Logout setBackgroundImage:imageGlobal forState:UIControlStateNormal];
    [Logout addTarget:self action:@selector(btLogoutTouchUpInside)
     forControlEvents:UIControlEventTouchUpInside];
    [Logout setShowsTouchWhenHighlighted:YES];
    
    UIBarButtonItem *leftButton =[[UIBarButtonItem alloc] initWithCustomView:Logout];
    self.navigationItem.leftBarButtonItem=leftButton;
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
                                   initWithTarget:self
                                   action:@selector(dismissKeyboard)];
    
    [self.view addGestureRecognizer:tap];
    
    [_btConfirm addTarget:self action:@selector(acc) forControlEvents:UIControlEventTouchUpInside];
    
    self.tfAddress.delegate = self;
    self.tfFullname.delegate = self;
    self.tfUsername.delegate = self;
    self.tfPassword.delegate = self;
    self.tfFreetime.delegate = self;
    self.tfConfirmPassword.delegate = self;
    self.tfHospital.delegate = self;
    
    
    
//    
//    _tableView.delegate = self;
//    _tableView.dataSource =self;
//    [self.tableView registerNib:[UINib nibWithNibName:@"BDRegissterCell" bundle:nil] forCellReuseIdentifier:@"BDRegissterCell"];

    
    // Do any additional setup after loading the view from its nib.
}
-(void)acc{
    if ([self.tfAddress.text length] == 0 || [self.tfAddress.text length] == 0 || [self.tfPassword.text length] == 0 || [_tfFreetime.text length] == 0) {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Warnning!"
                                                        message:@"Must complete information!"
                                                       delegate:self
                                              cancelButtonTitle:@"Cancel"
                                              otherButtonTitles:nil];
        [alert show];
        return;
    }
    else{
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Congratulations!"
                                                            message:@"You are already a member, go to blood test for active account!"
                                                           delegate:self
                                                  cancelButtonTitle:@"Cancel"
                                                  otherButtonTitles:nil];
            [alert show];
            return;
    }
    [self btLogoutTouchUpInside];
}

-(void)btLogoutTouchUpInside{
    [self.navigationController popViewControllerAnimated:YES];
    
}

#pragma mark - Setup keyboard

-(BOOL) textViewShouldReturn:(UITextField *)textField{
    return NO;
}

- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField {
    textField.text = @"";
    if (textField == _tfAddress || textField == _tfFullname || textField == _tfHospital || textField == _tfFreetime) {
        [UIView animateWithDuration:0.25 animations:^{
            self.view.frame = CGRectMake(0, -150, self.view.frame.size.width, self.view.frame.size.height);
        }];
    }
    
    return YES;
}


- (BOOL)textFieldShouldEndEditing:(UITextField *)textField {
    [self dismissKeyboard];
    return YES;
}



-(void)dismissKeyboard{
    [UIView animateWithDuration:0.25 animations:^{
        self.view.frame = CGRectMake(0, +63, self.view.frame.size.width, self.view.frame.size.height);
    }];
    //[self.view setFrame:CGRectMake(0,+11,320,460)];
    
    [self.tfAddress resignFirstResponder];
    [self.tfFullname resignFirstResponder];
    [self.tfUsername resignFirstResponder];
    [self.tfPassword resignFirstResponder];
    [self.tfFreetime resignFirstResponder];
    [self.tfConfirmPassword resignFirstResponder];
    [self.tfHospital resignFirstResponder];
   

}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
#pragma mark - Table view delegate



/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
