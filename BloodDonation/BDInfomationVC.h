//
//  BDInfomationVC.h
//  BloodDonation
//
//  Created by Le Phuc on 4/24/16.
//  Copyright © 2016 Lê Phúc. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BDInfomationVC : UIViewController <UITableViewDataSource,UITableViewDelegate,UITextViewDelegate>
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UIButton *btEditInfo;
@property (weak, nonatomic) IBOutlet UIButton *btLockAccount;

@end
