///Volumes/Data/Objective C/My project/BloodDonation/BloodDonation/BDGlobalVC.h
//  BDStatusCell.h
//  BloodDonation
//
//  Created by Le Phuc on 4/24/16.
//  Copyright © 2016 Lê Phúc. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BDStatusCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *lbTitle;
@property (weak, nonatomic) IBOutlet UILabel *lbStatus;
@property (weak, nonatomic) IBOutlet UIButton *btLike;
@property (weak, nonatomic) IBOutlet UIButton *btComment;
@property (weak, nonatomic) IBOutlet UILabel *lbNumberLike;
@property (weak, nonatomic) IBOutlet UILabel *lbNumberCommnnt;
@property (weak, nonatomic) IBOutlet UIButton *btShare;
@property (weak, nonatomic) IBOutlet UILabel *lbTime;

@end
