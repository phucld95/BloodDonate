//
//  BDGlobalVC.h
//  BloodDonation
//
//  Created by Le Phuc on 4/24/16.
//  Copyright © 2016 Lê Phúc. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BDGlobalVC : UIViewController <UITableViewDelegate,UITableViewDataSource>
@property (weak, nonatomic) IBOutlet UITableView *tableView;

@end
