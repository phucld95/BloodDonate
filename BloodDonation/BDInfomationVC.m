//
//  BDInfomationVC.m
//  BloodDonation
//
//  Created by Le Phuc on 4/24/16.
//  Copyright © 2016 Lê Phúc. All rights reserved.
//

#import "BDInfomationVC.h"
#import "Cell1.h"
#import "BDPostStatusCell.h"
#import "BDGlobalVC.h"
#import <FBSDKCoreKit/FBSDKCoreKit.h>
#import <FBSDKLoginKit/FBSDKLoginKit.h>

@interface BDInfomationVC ()

@end

@implementation BDInfomationVC

- (void)viewDidLoad {
    [super viewDidLoad];
    _tableView.delegate = self;
    _tableView.dataSource = self;
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
                                   initWithTarget:self
                                   action:@selector(dismissKeyboard)];
    
    [self.view addGestureRecognizer:tap];
    
    [self.tableView registerNib:[UINib nibWithNibName:@"Cell1" bundle:nil] forCellReuseIdentifier:@"Cell1"];
    [self.tableView registerNib:[UINib nibWithNibName:@"BDPostStatusCell" bundle:nil] forCellReuseIdentifier:@"BDPostStatusCell"];

    [_btEditInfo addTarget:self action:@selector(btEditInfoTouchUpInside) forControlEvents:UIControlEventTouchUpInside];
    [_btLockAccount addTarget:self action:@selector(btLockAccountTouchUpInside) forControlEvents:UIControlEventTouchUpInside];
    
    UIImage* imageGlobal = [UIImage imageNamed:@"global"];
    CGRect frameimg = CGRectMake(0, 0, imageGlobal.size.width, imageGlobal.size.height);
    UIButton *btGlobal = [[UIButton alloc] initWithFrame:frameimg];
    [btGlobal setBackgroundImage:imageGlobal forState:UIControlStateNormal];
    [btGlobal addTarget:self action:@selector(btGlobalTouchUpInside)
         forControlEvents:UIControlEventTouchUpInside];
    [btGlobal setShowsTouchWhenHighlighted:YES];
    
    UIBarButtonItem *rightButton =[[UIBarButtonItem alloc] initWithCustomView:btGlobal];
    self.navigationItem.rightBarButtonItem=rightButton;
    
    
    
    
    UIImage* imageHome = [UIImage imageNamed:@"home"];
    CGRect imh = CGRectMake(0, 0, imageHome.size.width, imageGlobal.size.height-10);
    UIButton *Logout = [[UIButton alloc] initWithFrame:imh];
    [Logout setBackgroundImage:imageHome forState:UIControlStateNormal];
    [Logout addTarget:self action:@selector(btLogoutTouchUpInside)
       forControlEvents:UIControlEventTouchUpInside];
    [Logout setShowsTouchWhenHighlighted:YES];
    
    UIBarButtonItem *leftButton =[[UIBarButtonItem alloc] initWithCustomView:Logout];
    self.navigationItem.leftBarButtonItem=leftButton;
    
    
    // Do any additional setup after loading the view from its nib.
}

-(void)dismissKeyboard{
    UITableViewCell *cell = [_tableView cellForRowAtIndexPath:[NSIndexPath indexPathForRow:0 inSection:0]];
    [((BDPostStatusCell*)cell).tvIneedYourHelp resignFirstResponder];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Action
-(void)send{
    [self dismissKeyboard];
    UITableViewCell *cell = [_tableView cellForRowAtIndexPath:[NSIndexPath indexPathForRow:0 inSection:0]];
    NSString *text = ((BDPostStatusCell*)cell).tvIneedYourHelp.text;
    
    FBSDKGraphRequest *request = [[FBSDKGraphRequest alloc]
                                  initWithGraphPath: @"/1697920880476385/feed"
                                  parameters:@{
                                               @"message":text,
                                               }
                                  HTTPMethod:@"POST"];
    
    [request startWithCompletionHandler:^(FBSDKGraphRequestConnection *connection,
                                          id result,
                                          NSError *error) {
        if (!error) {
            if ([result objectForKey:@"id"]) {
                UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Congratulations!"
                                                                message:@"Your story has been posted successfully"
                                                               delegate:nil
                                                      cancelButtonTitle:@"OK"
                                                      otherButtonTitles:nil];
                [alert show];
                ((BDPostStatusCell*)cell).tvIneedYourHelp.text = @"";
            }
        }
        
    }];
}


-(void)btLogoutTouchUpInside{
    [self.navigationController popViewControllerAnimated:YES];

}

-(void)btGlobalTouchUpInside{
    BDGlobalVC *view1 = [[BDGlobalVC alloc] initWithNibName:@"BDGlobalVC" bundle:nil];
    [self.navigationController pushViewController:view1 animated:YES];
}

-(void)btEditInfoTouchUpInside{
    
}

-(void)btLockAccountTouchUpInside{
    
}
-(void)textViewDidBeginEditing:(UITextView *)textView{
    
    
    UITableViewCell *cell = [_tableView cellForRowAtIndexPath:[NSIndexPath indexPathForRow:0 inSection:0]];
    [((BDPostStatusCell*)cell).tvIneedYourHelp setText:@""];
}

#pragma mark - Table view delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSLog(@"Row pressed!!");
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
    return 12;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.row == 0) {
        BDPostStatusCell *cell = [tableView dequeueReusableCellWithIdentifier:@"BDPostStatusCell"];
        [cell setSelectionStyle:UITableViewCellSelectionStyleNone];
        cell.tvIneedYourHelp.delegate = self;
        [cell.btSend addTarget:self action:@selector(send) forControlEvents:UIControlEventTouchUpInside];
        return cell;
    }
    Cell1 *cell = [tableView dequeueReusableCellWithIdentifier:@"Cell1"];
    [cell setSelectionStyle:UITableViewCellSelectionStyleNone];

    if (indexPath.row == 1) {
        cell.lbTitle.text = @"Full name:";
        cell.lbDetal.text = @"Lê Đình Phúc";
        return cell;
    }
    if (indexPath.row == 2) {
        cell.lbTitle.text = @"Blood group:";
        cell.lbDetal.text = @"AB";
        return cell;
    }
    if (indexPath.row == 3) {
        cell.lbTitle.text = @"The last test:";
        cell.lbDetal.text = @"24-01-2016";
        return cell;
    }
    if (indexPath.row == 4) {
        cell.lbTitle.text = @"The last donate:";
        cell.lbDetal.text = @"23-04-2016";
        return cell;
    }
    if (indexPath.row == 5) {
        cell.lbTitle.text = @"Health:";
        cell.lbDetal.text = @"Có dấu hiệu của bệnh máu khó đông!";
        return cell;
    }
    if (indexPath.row == 6) {
        cell.lbTitle.text = @"Health score:";
        cell.lbDetal.text = @"6";
        return cell;
    }
    if (indexPath.row == 7) {
        cell.lbTitle.text = @"User name:";
        cell.lbDetal.text = @"ledinhphuc@gmail.com";
        return cell;
    }
    if (indexPath.row == 8) {
        cell.lbTitle.text = @"Password:";
        cell.lbDetal.text = @"ledinhphuc";
        return cell;
    }
    if (indexPath.row == 9) {
        cell.lbTitle.text = @"Phone number:";
        cell.lbDetal.text = @"01689655741";
        return cell;
    }
    if (indexPath.row == 10) {
        cell.lbTitle.text = @"Hospital test:";
        cell.lbDetal.text = @"Bệnh viện Bạch Mai";
        return cell;
    }
    if (indexPath.row == 11) {
        cell.lbTitle.text = @"Address:";
        cell.lbDetal.text = @"Lai Xá - Hoài Đức - Hà Nội";
        return cell;
    }
    
    else{
        Cell1 *cell = [tableView dequeueReusableCellWithIdentifier:@"Cell1"];
        
        return cell;
    }
    
}

- (CGFloat) tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.row == 0) {
        return 197;
    }
    return 53;
}



/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
