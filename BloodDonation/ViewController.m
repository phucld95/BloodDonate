//
//  ViewController.m
//  BloodDonation
//
//  Created by Le Phuc on 4/24/16.
//  Copyright © 2016 Lê Phúc. All rights reserved.
//

#import "ViewController.h"
#import "BDInfomationVC.h"
#import <FBSDKCoreKit/FBSDKCoreKit.h>
#import <FBSDKLoginKit/FBSDKLoginKit.h>
#import "UIColor+flat.h"
#import "BDRegisterAccountVC.h"
@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [[UINavigationBar appearance] setTintColor:[UIColor whiteColor]];

    _btFacebookLogin.layer.cornerRadius = 5;
    _btFacebookLogin.layer.masksToBounds = YES;
    _btGoogle.layer.cornerRadius = 5;
    _btGoogle.layer.masksToBounds = YES;
    _btTwiter.layer.cornerRadius = 5;
    _btTwiter.layer.masksToBounds = YES;
    
    _btLogin.layer.cornerRadius = 5;
    _btLogin.layer.masksToBounds = YES;
    
    _tfUsermane.delegate = self;
    _tfPassword.delegate = self;
    
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
                                   initWithTarget:self
                                   action:@selector(dismissKeyboard)];
    
    [self.view addGestureRecognizer:tap];
    
    // Do any additional setup after loading the view, typically from a nib.
    [_btLogin addTarget:self action:@selector(btLoginTouchUpIndise2) forControlEvents:UIControlEventTouchUpInside];
    [_btFacebookLogin addTarget:self action:@selector(btLoginTouchUpInside) forControlEvents:UIControlEventTouchUpInside];
    [_btRegister addTarget:self action:@selector(btRegisterTouchUpInside) forControlEvents:UIControlEventTouchUpInside];
    
    
    
    self.navigationController.navigationBar.barTintColor = [UIColor colorWithHexCode:@"d81d36"];
    self.navigationController.navigationBar.translucent = NO;
    
}

-(void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Action

-(void)btRegisterTouchUpInside{
    BDRegisterAccountVC *view1 = [[BDRegisterAccountVC alloc] initWithNibName:@"BDRegisterAccountVC" bundle:nil];
    [self.navigationController pushViewController:view1 animated:YES];
}

-(void) requestPermission{
    if ([[FBSDKAccessToken currentAccessToken] hasGranted:@"publish_actions"]) {
        BDInfomationVC *view1 = [[BDInfomationVC alloc] initWithNibName:@"BDInfomationVC" bundle:nil];
        [self.navigationController pushViewController:view1 animated:YES];
    } else {
        FBSDKLoginManager *loginManager = [[FBSDKLoginManager alloc] init];
        [loginManager logInWithPublishPermissions:@[@"publish_actions"]
                               fromViewController:self
                                          handler:^(FBSDKLoginManagerLoginResult *result, NSError *error) {
                                              BDInfomationVC *view1 = [[BDInfomationVC alloc] initWithNibName:@"BDInfomationVC" bundle:nil];
                                              [self.navigationController pushViewController:view1 animated:YES];
                                          }];
    }
}


-(void)btLoginTouchUpInside{
    FBSDKLoginManager *login = [[FBSDKLoginManager alloc] init];
    [login
     logInWithReadPermissions: @[@"public_profile"]
     fromViewController:self
     handler:^(FBSDKLoginManagerLoginResult *result, NSError *error) {
         if (error) {
             NSLog(@"Process error");
         } else if (result.isCancelled) {
             NSLog(@"Cancelled");
         } else {
             NSLog(@"Logged in");
             [self requestPermission];
         }
     }];    
}

-(void)btLoginTouchUpIndise2{
    NSString *user = _tfUsermane.text;
    NSString *pass = _tfPassword.text;
    if (user == nil|| pass == nil || [user length] == 0 || [pass length] == 0) {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Warnning!"
                                                        message:@"Username and password can not be empty!"
                                                       delegate:self
                                              cancelButtonTitle:@"Cancel"
                                              otherButtonTitles:nil];
        [alert show];
        return;
    }
    if (![pass isEqualToString:@"ledinhphuc"]) {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Warnning!"
                                                        message:@"Password woong!"
                                                       delegate:self
                                              cancelButtonTitle:@"Cancel"
                                              otherButtonTitles:nil];
        [alert show];
        return;
    }
    else{
        BDInfomationVC *view1 = [[BDInfomationVC alloc] initWithNibName:@"BDInfomationVC" bundle:nil];
        [self.navigationController pushViewController:view1 animated:YES];
    }
}

#pragma mark - Textfield delegate.






-(void)dismissKeyboard{
    [_tfPassword resignFirstResponder];
    [_tfUsermane resignFirstResponder];
}


@end
